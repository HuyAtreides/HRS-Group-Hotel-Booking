package com.hrs.hotelbooking.domain.exception;

public class InvalidBooking extends RuntimeException {

    public InvalidBooking(String s) {
        super(s);
    }
}
