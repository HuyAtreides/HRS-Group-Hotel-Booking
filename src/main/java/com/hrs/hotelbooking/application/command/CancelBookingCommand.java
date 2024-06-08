package com.hrs.hotelbooking.application.command;

import com.hrs.hotelbooking.domain.model.BookingDetails;
import java.time.Instant;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CancelBookingCommand {
    private UUID bookingDetailsId;
}
