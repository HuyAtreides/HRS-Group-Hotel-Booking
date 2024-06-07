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

    public BookingModificationRequest(BookingDetails bookingDetails, User modifier,
            Instant modifiedAt,
            LocalDate newCheckInDate, LocalDate newCheckOutDate) {
        if (newCheckInDate == null && newCheckOutDate == null) {
            throw new IllegalArgumentException("New check in date and new check out date can not be both null");
        }

        this.bookingDetails = bookingDetails;
        this.modifier = modifier;
        this.modifiedAt = modifiedAt;
        this.newCheckInDate = newCheckInDate;
        this.newCheckOutDate = newCheckOutDate;
    }
}
