package com.upgrad.BookingService.service;

import com.upgrad.BookingService.dao.BookingDao;
import com.upgrad.BookingService.entity.BookingInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    public BookingDao bookingDao;

    @Override
    public BookingInfoEntity saveBookingDetails(BookingInfoEntity bookingInfoEntity) {
        return bookingDao.save(bookingInfoEntity);
    }

    @Override
    public BookingInfoEntity findByBookingId(int bookingId) {
        Optional<BookingInfoEntity> allBookingInfoEntity = bookingDao.findById(bookingId);
        BookingInfoEntity bookingInfoEntity = allBookingInfoEntity.get();

        return bookingInfoEntity;
    }
}
