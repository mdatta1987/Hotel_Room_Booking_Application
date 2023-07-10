package com.upgrad.BookingService.service;

import com.upgrad.BookingService.entity.BookingInfoEntity;

public interface BookingService {
    public BookingInfoEntity saveBookingDetails(BookingInfoEntity bookingInfoEntity);
    public BookingInfoEntity findByBookingId(int bookingId);
}
