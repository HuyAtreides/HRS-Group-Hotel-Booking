package com.hrs.hotelbooking.unit;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.hrs.hotelbooking.domain.exception.InvalidBookingModification;
import com.hrs.hotelbooking.domain.model.BookedRoom;
import com.hrs.hotelbooking.domain.model.BookingDetails;
import com.hrs.hotelbooking.domain.model.BookingRequest;
import com.hrs.hotelbooking.domain.model.CancelBookingRequest;
import com.hrs.hotelbooking.domain.model.Hotel;
import com.hrs.hotelbooking.domain.model.Location;
import com.hrs.hotelbooking.domain.model.Room;
import com.hrs.hotelbooking.domain.model.User;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class CancelBookingTest {

    private final UUID TEST_LOCATION_ID = UUID.randomUUID();
    private final UUID TEST_ID = UUID.randomUUID();
    private final Room TEST_ROOM = Room.builder()
            .id(TEST_ID)
            .availability(3)
            .price(BigDecimal.valueOf(15.5))
            .build();
    private final Location TEST_LOCATION = Location.builder()
            .id(TEST_LOCATION_ID)
            .rooms(
                    Set.of(
                            TEST_ROOM
                    )
            )
            .build();
    private final User TEST_MODIFIER = User.builder()
            .id(UUID.fromString("d5ba2bf3-cb3b-41aa-8a5c-6c20b0da6aa9"))
            .build();
    private final Hotel TEST_HOTEL = Hotel.builder()
            .locations(Set.of(TEST_LOCATION))
            .build();
    private final BookingDetails CANCEL_BOOKING_DETAILS_TEST = TEST_HOTEL.book(
            BookingRequest.builder()
                    .location(TEST_LOCATION)
                    .checkInDate(LocalDate.parse("2024-04-11"))
                    .checkOutDate(LocalDate.parse("2024-04-27"))
                    .rooms(Set.of(
                            BookedRoom.builder()
                                    .quantity(2)
                                    .room(TEST_ROOM)
                                    .build()
                    ))
                    .user(TEST_MODIFIER)
                    .bookedAt(Instant.parse("2024-04-05T09:17:00Z"))
                    .build(),
            Set.of());


    @Test
    void cancel_booking_after_3_days_from_booking_date_should_be_invalid() {
        assertThrows(
                InvalidBookingModification.class,
                () -> TEST_HOTEL.cancelBooking(
                        CancelBookingRequest.builder()
                                .bookingDetails(CANCEL_BOOKING_DETAILS_TEST)
                                .cancelAt(Instant.parse("2024-04-08T04:20:00Z"))
                                .modifier(TEST_MODIFIER)
                                .build()
                ));
    }

    @Test
    void cancel_booking_after_check_in_date_should_be_invalid() {
        assertThrows(
                InvalidBookingModification.class,
                () -> TEST_HOTEL.cancelBooking(
                        CancelBookingRequest.builder()
                                .bookingDetails(CANCEL_BOOKING_DETAILS_TEST)
                                .cancelAt(Instant.parse("2024-04-12T10:20:00Z"))
                                .modifier(TEST_MODIFIER)
                                .build()
                ));
    }

    @Test
    void valid_booking_cancellation_should_successful() {
        assertDoesNotThrow(
                () -> TEST_HOTEL.cancelBooking(
                        CancelBookingRequest.builder()
                                .bookingDetails(CANCEL_BOOKING_DETAILS_TEST)
                                .cancelAt(Instant.parse("2024-04-07T10:30:00Z"))
                                .modifier(TEST_MODIFIER)
                                .build()
                ));
    }
}
