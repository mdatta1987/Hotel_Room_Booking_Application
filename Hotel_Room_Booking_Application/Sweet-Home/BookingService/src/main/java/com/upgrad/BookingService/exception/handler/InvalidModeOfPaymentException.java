package com.upgrad.BookingService.exception.handler;

public class InvalidModeOfPaymentException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InvalidModeOfPaymentException(String message) {
        super(message);
    }
}
