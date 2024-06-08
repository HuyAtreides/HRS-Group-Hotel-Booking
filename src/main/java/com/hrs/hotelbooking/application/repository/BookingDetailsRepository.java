package com.hrs.hotelbooking.application.repository;

import com.hrs.hotelbooking.application.Page;
import com.hrs.hotelbooking.application.command.BookHotelCommand;
import com.hrs.hotelbooking.application.command.BookingModificationCommand;
import com.hrs.hotelbooking.application.query.PageOptions;
import com.hrs.hotelbooking.domain.model.BookingDetails;
import com.hrs.hotelbooking.domain.model.User;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.domain.Pageable;

public interface BookingDetailsRepository {
    Set<BookingDetails> findAllOverlappingBookings(BookHotelCommand bookHotelCommand);

    Set<BookingDetails> findAllOverlappingBookings(BookingModificationCommand command);

    Page<BookingDetails> findAllBookingDetailsOfUser(User user, PageOptions pageOptions);

    BookingDetails save(BookingDetails bookingDetails);

    BookingDetails findById(UUID uuid);

    void delete(BookingDetails bookingDetails);
}
