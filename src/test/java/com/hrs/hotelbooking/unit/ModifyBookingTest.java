package com.hrs.hotelbooking.unit;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.hrs.hotelbooking.domain.exception.InvalidBooking;
import com.hrs.hotelbooking.domain.exception.InvalidBookingModification;
import com.hrs.hotelbooking.domain.model.BookedRoom;
import com.hrs.hotelbooking.domain.model.BookingDetails;
import com.hrs.hotelbooking.domain.model.BookingModificationRequest;
import com.hrs.hotelbooking.domain.model.BookingRequest;
import com.hrs.hotelbooking.domain.model.Hotel;
import com.hrs.hotelbooking.domain.model.Location;
import com.hrs.hotelbooking.domain.model.Room;
import com.hrs.hotelbooking.domain.model.User;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class ModifyBookingTest {

    private final UUID TEST_LOCATION_ID = UUID.randomUUID();
    private final UUID TEST_ROOM_1_ID = UUID.randomUUID();
    private final Room TEST_ROOM_1 = Room.builder()
            .id(TEST_ROOM_1_ID)
            .availability(3)
            .price(BigDecimal.valueOf(27.7))
            .build();
    private final Location TEST_LOCATION = Location.builder()
            .id(TEST_LOCATION_ID)
            .rooms(
                    Set.of(
                            TEST_ROOM_1
                    )
            )
            .build();
    private final Hotel TEST_HOTEL = Hotel.builder()
            .locations(Set.of(TEST_LOCATION))
            .build();

    private final Instant BOOKED_AT = Instant.parse("2024-03-01T00:00:00Z");
    private final User TEST_MODIFIER = User.builder()
            .id(UUID.fromString("d5ba2bf3-cb3b-41aa-8a5c-6c20b0da6aa9"))
            .build();
    private final BookingDetails MODIFYING_BOOKING_DETAILS = TEST_HOTEL.book(
            BookingRequest.builder()
                    .rooms(Set.of(
                            BookedRoom.builder()
                                    .room(TEST_ROOM_1)
                                    .quantity(2)
                                    .build()
                    ))
                    .location(TEST_LOCATION)
                    .bookedAt(BOOKED_AT)
                    .user(TEST_MODIFIER)
                    .checkInDate(LocalDate.parse("2024-03-23"))
                    .checkOutDate(LocalDate.parse("2024-03-25"))
                    .build(),
            Set.of()
    );
    private final BookingDetails BOOKING_DETAILS_1 = TEST_HOTEL.book(
            BookingRequest.builder()
                    .rooms(Set.of(
                            BookedRoom.builder()
                                    .room(TEST_ROOM_1)
                                    .quantity(1)
                                    .build()
                    ))
                    .location(TEST_LOCATION)
                    .bookedAt(BOOKED_AT)
                    .checkInDate(LocalDate.parse("2024-03-15"))
                    .checkOutDate(LocalDate.parse("2024-03-19"))
                    .build(),
            Set.of()
    );

    private final BookingDetails BOOKING_DETAILS_2 = TEST_HOTEL.book(
            BookingRequest.builder()
                    .rooms(Set.of(
                            BookedRoom.builder()
                                    .room(TEST_ROOM_1)
                                    .quantity(1)
                                    .build()
                    ))
                    .location(TEST_LOCATION)
                    .bookedAt(BOOKED_AT)
                    .checkInDate(LocalDate.parse("2024-03-17"))
                    .checkOutDate(LocalDate.parse("2024-03-22"))
                    .build(),
            Set.of()
    );

    @Test
    void modify_booking_staying_period_result_in_unavailable_rooms_should_fail() {
        assertThrows(InvalidBooking.class, () -> {
            TEST_HOTEL.modifyBooking(
                    BookingModificationRequest.builder()
                            .modifiedAt(Instant.parse("2024-03-02T09:30:00Z"))
                            .newCheckInDate(LocalDate.parse("2024-03-20"))
                            .newCheckOutDate(LocalDate.parse("2024-03-25"))
                            .bookingDetails(MODIFYING_BOOKING_DETAILS)
                            .modifier(TEST_MODIFIER)
                            .build(),
                    Set.of(
                            BOOKING_DETAILS_1,
                            BOOKING_DETAILS_2
                    )
            );
        });
    }

    @Test
    void modify_booking_after_2_days_since_book_date_should_be_invalid() {
        assertThrows(InvalidBookingModification.class, () -> {
            TEST_HOTEL.modifyBooking(
                    BookingModificationRequest.builder()
                            .modifiedAt(Instant.parse("2024-03-04T09:30:00Z"))
                            .newCheckInDate(LocalDate.parse("2024-03-20"))
                            .newCheckOutDate(LocalDate.parse("2024-03-25"))
                            .bookingDetails(MODIFYING_BOOKING_DETAILS)
                            .modifier(TEST_MODIFIER)
                            .build(),
                    Set.of(
                            BOOKING_DETAILS_1,
                            BOOKING_DETAILS_2
                    )
            );
        });
    }

    @Test
    void modify_booking_after_check_in_date_should_be_invalid() {
        assertThrows(InvalidBookingModification.class, () -> {
            TEST_HOTEL.modifyBooking(
                    BookingModificationRequest.builder()
                            .modifiedAt(Instant.parse("2024-03-25T09:30:00Z"))
                            .newCheckInDate(LocalDate.parse("2024-03-26"))
                            .newCheckOutDate(LocalDate.parse("2024-03-27"))
                            .bookingDetails(MODIFYING_BOOKING_DETAILS)
                            .modifier(TEST_MODIFIER)
                            .build(),
                    Set.of()
            );
        });
    }

    @Test
    void valid_modification_should_result_in_booking_details_update() {
        Instant modifiedAt = Instant.parse("2024-03-02T09:30:00Z");
        LocalDate newCheckInDate = LocalDate.parse("2024-03-26");
        LocalDate newCheckOutDate = LocalDate.parse("2024-03-27");

        TEST_HOTEL.modifyBooking(
                BookingModificationRequest.builder()
                        .modifiedAt(modifiedAt)
                        .newCheckInDate(newCheckInDate)
                        .newCheckOutDate(newCheckOutDate)
                        .bookingDetails(MODIFYING_BOOKING_DETAILS)
                        .modifier(TEST_MODIFIER)
                        .build(),
                Set.of()
        );

        assertAll("Booking details updated correctly",
                () -> assertEquals(MODIFYING_BOOKING_DETAILS.getLastModifiedAt(), modifiedAt),
                () -> assertEquals(MODIFYING_BOOKING_DETAILS.getCheckInDate(), newCheckInDate),
                () -> assertEquals(MODIFYING_BOOKING_DETAILS.getCheckOutDate(), newCheckOutDate),
                () -> assertEquals(0, MODIFYING_BOOKING_DETAILS.getTotalPrice()
                        .compareTo(BigDecimal.valueOf(55.4)))
        );
    }
}
