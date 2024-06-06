package com.hrs.hotelbooking.domain.model;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingRequest {
    private User user;
    private Instant bookedAt;
    private Location location;
    private Set<BookedRoom> rooms;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
}
