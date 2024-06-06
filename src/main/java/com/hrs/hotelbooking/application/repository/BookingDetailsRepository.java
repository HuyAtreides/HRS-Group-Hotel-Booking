package com.hrs.hotelbooking.application.repository;

import com.hrs.hotelbooking.application.command.BookHotelCommand;
import com.hrs.hotelbooking.domain.model.BookingDetails;
import java.util.List;
import java.util.Set;

public interface BookingDetailsRepository {
    Set<BookingDetails> findAllOverlappingBookings(BookHotelCommand bookHotelCommand);

    BookingDetails save(BookingDetails bookingDetails);
}
