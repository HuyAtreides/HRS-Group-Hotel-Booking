package com.hrs.hotelbooking.domain.exception;

public class InvalidBookingModification extends RuntimeException {
    public InvalidBookingModification(String s) {
        super(s);
    }
}
