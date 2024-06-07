package com.hrs.hotelbooking.domain.model;

import java.time.Instant;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingModificationRequest {
    private BookingDetails bookingDetails;
    private User modifier;
    private Instant modifiedAt;
    private LocalDate newCheckInDate;
    private LocalDate newCheckOutDate;
}
