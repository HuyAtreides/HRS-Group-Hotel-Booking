package com.hrs.hotelbooking.domain.exception;

public class InvalidBookingCancellation extends RuntimeException {
    public InvalidBookingCancellation(String s) {
        super(s);
    }
}
