package com.hrs.hotelbooking.application.command;

import java.time.LocalDate;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingModificationCommand {
    private UUID existingBookingId;
    private LocalDate newCheckInDate;
    private LocalDate newCheckOutDate;
}
