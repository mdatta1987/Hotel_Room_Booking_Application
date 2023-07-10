package com.upgrad.BookingService.exception.handler;

import com.upgrad.BookingService.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    private String INVALID_MODE_OF_PAYMENT = "Invalid mode of payment";
    private String INVALID_BOOKING_ID = "Invalid Booking Id";

    @ExceptionHandler(InvalidModeOfPaymentException.class)
    public final ResponseEntity<ErrorResponse> handleInvalidModeOfPaymentException(InvalidModeOfPaymentException e, WebRequest req) {
        List<String> errorDetails = new ArrayList<String>();
        errorDetails.add(e.getLocalizedMessage());
        ErrorResponse response = new ErrorResponse(INVALID_MODE_OF_PAYMENT, errorDetails);

        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidBookingIdException.class)
    public final ResponseEntity<ErrorResponse> handleInvalidBookingIdException(InvalidBookingIdException e, WebRequest req) {
        List<String> errorDetails = new ArrayList<String>();
        errorDetails.add(e.getLocalizedMessage());
        ErrorResponse response = new ErrorResponse(INVALID_BOOKING_ID, errorDetails);

        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
