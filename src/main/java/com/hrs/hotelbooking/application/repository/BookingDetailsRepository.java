package com.hrs.hotelbooking.application.repository;

import com.hrs.hotelbooking.application.command.BookHotelCommand;
import com.hrs.hotelbooking.application.command.BookingModificationCommand;
import com.hrs.hotelbooking.domain.model.BookingDetails;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface BookingDetailsRepository {
    Set<BookingDetails> findAllOverlappingBookings(BookHotelCommand bookHotelCommand);

    Set<BookingDetails> findAllOverlappingBookings(BookingModificationCommand command);

    BookingDetails save(BookingDetails bookingDetails);

    BookingDetails findById(UUID uuid);
}
