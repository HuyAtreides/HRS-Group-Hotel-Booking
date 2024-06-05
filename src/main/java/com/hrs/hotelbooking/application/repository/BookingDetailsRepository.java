package com.hrs.hotelbooking.application.repository;

import com.hrs.hotelbooking.domain.model.BookingDetails;
import java.util.List;

public interface BookingDetailsRepository {
    List<BookingDetails> findAllOverlappingBooking();
}
