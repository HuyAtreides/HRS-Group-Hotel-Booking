package com.hrs.hotelbooking.application.command;

import com.hrs.hotelbooking.domain.model.BookedRoom;
import com.hrs.hotelbooking.domain.model.Location;
import com.hrs.hotelbooking.domain.model.User;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookHotelCommand {
    private UUID locationId;
    private UUID hotelId;
    private Set<BookRoomCommand> rooms;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
}
