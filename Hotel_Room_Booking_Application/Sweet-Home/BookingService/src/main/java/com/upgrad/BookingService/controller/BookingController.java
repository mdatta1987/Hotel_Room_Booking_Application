package com.upgrad.BookingService.controller;

import com.upgrad.BookingService.dto.BookingDTO;
import com.upgrad.BookingService.dto.TransactionDTO;
import com.upgrad.BookingService.entity.BookingInfoEntity;
import com.upgrad.BookingService.exception.handler.InvalidBookingIdException;
import com.upgrad.BookingService.exception.handler.InvalidModeOfPaymentException;
import com.upgrad.BookingService.service.BookingService;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

@RestController
@RequestMapping(value = "/hotel")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping(value = "/booking", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookingInfoEntity> createBooking(@RequestBody BookingDTO bookingDTO) {
        BookingInfoEntity newBookingInfoEntity = modelMapper.map(bookingDTO, BookingInfoEntity.class);
        int numberOfRooms = newBookingInfoEntity.getNumOfRooms();
        Random rand = new Random();
        int upperBound = 100;

        /* ArrayList to store the room numbers generated randomly */
        ArrayList<String> roomNumberList = new ArrayList<String>();

        for (int i = 0; i < numberOfRooms; i++){
            roomNumberList.add(String.valueOf(rand.nextInt(upperBound)));
        }

        /* Converting the ArrayList of numbers to a string for storing in the String variable roomNumbers */
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < roomNumberList.size(); i++) {
            sb.append(roomNumberList.get(i));

            if(i != roomNumberList.size() -1) {
                sb.append(",");
            }
        }

        String roomsAllocated = sb.toString();
        newBookingInfoEntity.setRoomNumbers(roomsAllocated);
        Date bookingDate = new Date();
        newBookingInfoEntity.setBookedOn(bookingDate);

        /* Logic to calculate the number of days between the toDate and fromDate */
        org.joda.time.LocalDate dateBefore = LocalDate.fromDateFields(newBookingInfoEntity.getFromDate());
        org.joda.time.LocalDate dateAfter = LocalDate.fromDateFields(newBookingInfoEntity.getToDate());
        long totalDays = Math.abs(Days.daysBetween(dateBefore, dateAfter).getDays());
        int roomPrice = (int) (1000 * numberOfRooms * totalDays);
        newBookingInfoEntity.setRoomPrice(roomPrice);
        BookingInfoEntity savedBookingInfoEntity = bookingService.saveBookingDetails(newBookingInfoEntity);
        BookingDTO savedBookingDTO = modelMapper.map(savedBookingInfoEntity, BookingDTO.class);

        return new ResponseEntity(savedBookingDTO, HttpStatus.CREATED);
    }

    @PostMapping(value = "/booking/{bookingId}/transaction", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {"*/*"})
    public ResponseEntity<BookingInfoEntity> getTransactionId(@PathVariable("bookingId") int bookingId, @RequestBody TransactionDTO transactionDTO) {
        if(transactionDTO.getPaymentMode().equals("UPI") || transactionDTO.getPaymentMode().equals("CARD")) {
        } else {
            throw new InvalidModeOfPaymentException("Invalid mode of payment");
        }

        /* RestTemplate to establish synchronous communication with the Payment service */
        RestTemplate restTemplate = new RestTemplate();
        String uri = "http://localhost:9191/payment/transaction";
        Integer transactionId = restTemplate.postForObject(uri, transactionDTO, Integer.class);

        if(transactionId == 0) {
            throw new InvalidBookingIdException("Invalid Booking Id");
        }

        /* Updating the transactionId received from the Payment service */
        BookingInfoEntity paidBookingInfoEntity = bookingService.findByBookingId(bookingId);
        paidBookingInfoEntity.setTransactionId(transactionId);
        BookingInfoEntity savePaidBookingInfoEntity = bookingService.saveBookingDetails(paidBookingInfoEntity);
        BookingDTO savedBookingDTO = modelMapper.map(savePaidBookingInfoEntity, BookingDTO.class);

        if(transactionId != 0) {
            String message = "Booking confirmed for user with aadhaar number: "
                    + savePaidBookingInfoEntity.getAadharNumber()
                    + "    |    "
                    + "Here are the booking details:    " + savePaidBookingInfoEntity.toString();

            System.out.println(message);
        }

        return new ResponseEntity(savedBookingDTO, HttpStatus.CREATED);
    }
}
