package com.hrs.hotelbooking.unit;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.hrs.hotelbooking.domain.exception.InvalidBooking;
import com.hrs.hotelbooking.domain.model.BookedRoom;
import com.hrs.hotelbooking.domain.model.BookingDetails;
import com.hrs.hotelbooking.domain.model.BookingRequest;
import com.hrs.hotelbooking.domain.model.Hotel;
import com.hrs.hotelbooking.domain.model.Location;
import com.hrs.hotelbooking.domain.model.Room;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class BookingHotelTest {

    private final UUID TEST_LOCATION_ID = UUID.randomUUID();
    private final UUID TEST_ROOM_1_ID = UUID.randomUUID();
    private final UUID TEST_ROOM_2_ID = UUID.randomUUID();
    private final UUID TEST_ROOM_3_ID = UUID.randomUUID();
    private final Room TEST_ROOM_1 = Room.builder()
            .id(TEST_ROOM_1_ID)
            .availability(3)
            .price(BigDecimal.valueOf(20.5))
            .build();
    private final Room TEST_ROOM_2 = Room.builder()
            .id(TEST_ROOM_2_ID)
            .availability(5)
            .price(BigDecimal.valueOf(15))
            .build();

    private final Room TEST_ROOM_3 = Room.builder()
            .id(TEST_ROOM_3_ID)
            .availability(2)
            .price(BigDecimal.valueOf(17))
            .build();
    private final Location TEST_LOCATION = Location.builder()
            .id(TEST_LOCATION_ID)
            .rooms(
                    Set.of(
                            TEST_ROOM_1,
                            TEST_ROOM_2
                    )
            )
            .build();
    private final Hotel TEST_HOTEL = Hotel.builder()
            .locations(Set.of(TEST_LOCATION))
            .build();
    private final Instant BOOKED_AT = Instant.parse("2024-03-01T00:00:00Z");
    private final LocalDate CHECK_IN_DATE = LocalDate.parse("2024-03-18");
    private final LocalDate CHECK_OUT_DATE = LocalDate.parse("2024-03-24");

    BookingDetails bookRoom(LocalDate checkIn, LocalDate checkOut, Room room, int quantity) {
        return TEST_HOTEL.book(
                BookingRequest.builder()
                        .rooms(Set.of(
                                BookedRoom.builder()
                                        .room(room)
                                        .quantity(quantity)
                                        .build()
                        ))
                        .location(TEST_LOCATION)
                        .bookedAt(BOOKED_AT)
                        .checkInDate(checkIn)
                        .checkOutDate(checkOut)
                        .build(),
                Set.of()
        );
    }

    @Test
    void booking_when_booking_rooms_quantity_can_not_be_satisfied_should_be_invalid() {
        BookingDetails overlappingBookingDetails1 = bookRoom(
                LocalDate.parse("2024-03-15"),
                LocalDate.parse("2024-03-19"),
                TEST_ROOM_1,
                1
        );
        BookingDetails overlappingBookingDetails2 = bookRoom(
                LocalDate.parse("2024-03-20"),
                LocalDate.parse("2024-03-21"),
                TEST_ROOM_1,
                1
        );
        BookingDetails overlappingBookingDetails3 = bookRoom(
                LocalDate.parse("2024-03-19"),
                LocalDate.parse("2024-03-23"),
                TEST_ROOM_2,
                3
        );

        BookingRequest bookingRequest = BookingRequest.builder()
                .checkInDate(CHECK_IN_DATE)
                .checkOutDate(CHECK_OUT_DATE)
                .location(TEST_LOCATION)
                .rooms(Set.of(
                        BookedRoom.builder()
                                .quantity(2)
                                .room(TEST_ROOM_1)
                                .build(),
                        BookedRoom.builder()
                                .quantity(2)
                                .room(TEST_ROOM_2)
                                .build()
                ))
                .build();

        assertThrows(InvalidBooking.class, () -> {
            TEST_HOTEL.book(
                    bookingRequest,
                    Set.of(
                            overlappingBookingDetails1,
                            overlappingBookingDetails2,
                            overlappingBookingDetails3
                    )
            );
        });
    }

    @Test
    void booking_when_booking_rooms_quantity_can_be_satisfied_should_be_successful() {
        BookingDetails overlappingBookingDetails1 = bookRoom(
                LocalDate.parse("2024-03-15"),
                LocalDate.parse("2024-03-19"),
                TEST_ROOM_1,
                1
        );
        BookingDetails overlappingBookingDetails2 = bookRoom(
                LocalDate.parse("2024-03-20"),
                LocalDate.parse("2024-03-21"),
                TEST_ROOM_1,
                1
        );
        BookingDetails overlappingBookingDetails3 = bookRoom(
                LocalDate.parse("2024-03-19"),
                LocalDate.parse("2024-03-23"),
                TEST_ROOM_2,
                3
        );

        Set<BookedRoom> bookingRooms = Set.of(
                BookedRoom.builder()
                        .quantity(1)
                        .room(TEST_ROOM_1)
                        .build(),
                BookedRoom.builder()
                        .quantity(2)
                        .room(TEST_ROOM_2)
                        .build()
        );
        BookingRequest bookingRequest = BookingRequest.builder()
                .checkInDate(CHECK_IN_DATE)
                .checkOutDate(CHECK_OUT_DATE)
                .bookedAt(BOOKED_AT)
                .location(TEST_LOCATION)
                .rooms(bookingRooms)
                .build();

        BookingDetails bookingDetails = TEST_HOTEL.book(
                bookingRequest,
                Set.of(
                        overlappingBookingDetails1,
                        overlappingBookingDetails2,
                        overlappingBookingDetails3
                )
        );
        assertAll("Correct Booking Details",
                () -> assertEquals(bookingDetails.getBookedAt(), BOOKED_AT),
                () -> assertEquals(0, BigDecimal.valueOf(303)
                        .compareTo(bookingDetails.getTotalPrice())),
                () -> assertTrue(bookingDetails.getBookedRooms().containsAll(bookingRooms)),
                () -> assertEquals(TEST_HOTEL, bookingDetails.getHotel())
        );
    }

    @Test
    void booking_rooms_do_not_belong_to_hotel_should_be_invalid() {
        BookingRequest bookingRequest = BookingRequest.builder()
                .checkInDate(CHECK_IN_DATE)
                .checkOutDate(CHECK_OUT_DATE)
                .location(TEST_LOCATION)
                .rooms(Set.of(
                        BookedRoom.builder()
                                .quantity(2)
                                .room(TEST_ROOM_3)
                                .build()
                ))
                .build();

        assertThrows(InvalidBooking.class, () -> {
            TEST_HOTEL.book(
                    bookingRequest,
                    Set.of()
            );
        });
    }
}
