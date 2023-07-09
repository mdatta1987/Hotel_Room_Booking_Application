package com.upgrad.bookmyconsultation.exception;

public class SlotUnavailableException extends RuntimeException {
    public Object getDescription() {
        return "The selected slot is not available.";
    }
}
