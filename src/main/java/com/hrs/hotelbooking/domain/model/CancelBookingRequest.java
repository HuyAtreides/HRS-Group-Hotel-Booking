package com.hrs.hotelbooking.domain.model;

import java.time.Instant;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CancelBookingRequest {
    private User modifier;
    private Instant cancelAt;
    private BookingDetails bookingDetails;
}
