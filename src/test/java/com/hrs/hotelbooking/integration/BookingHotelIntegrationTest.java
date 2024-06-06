package com.hrs.hotelbooking.integration;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.hrs.hotelbooking.application.AuthenticatedUserHolderService;
import com.hrs.hotelbooking.application.CurrentDateTimeService;
import com.hrs.hotelbooking.application.HotelService;
import com.hrs.hotelbooking.application.command.BookHotelCommand;
import com.hrs.hotelbooking.application.command.BookRoomCommand;
import com.hrs.hotelbooking.domain.model.BookingDetails;
import com.hrs.hotelbooking.domain.model.User;
import com.hrs.hotelbooking.infra.database.postgres.SpringBookingDetailsRepository;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@IntegrationTest
@Sql(value = "classpath:db/script/populate-booking-details.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "classpath:db/script/clean-booking-details.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
public class BookingHotelIntegrationTest {

    @Autowired
    private HotelService hotelService;

    @MockBean
    private CurrentDateTimeService currentDateTimeService;

    @MockBean
    private AuthenticatedUserHolderService  authenticatedUserHolderService;

    @Autowired
    private SpringBookingDetailsRepository bookingDetailsRepository;

    @BeforeEach
    public void mockCurrentDateTimeService() {
        Instant mockedDateTime = Instant.parse("2024-03-10T00:00:00Z");
        when(currentDateTimeService.getCurrentDateTime())
                .thenReturn(mockedDateTime);
    }

    @BeforeEach
    public void mockAuthenticatedUserService() {
        User mockedUser = User.builder()
                .id(UUID.fromString("d5ba2bf3-cb3b-41aa-8a5c-6c20b0da6aa9"))
                .email("phangiahuy2001@gmail.com")
                .firstName("Phan")
                .lastName("Huy")
                .build();
        when(authenticatedUserHolderService.getAuthenticatedUser())
                .thenReturn(mockedUser);
    }

    @Test
    void book_hotel_with_valid_info_then_there_should_be_a_booking_details_in_database() {
        final UUID TEST_HOTEL_ID = UUID.fromString("72314052-2101-42a5-aafe-c9e1326591c2");
        final UUID TEST_LOCATION_ID = UUID.fromString("52920a66-f256-4e6a-8097-b84f19c4f490");
        final UUID TEST_ROOM_ID_1 = UUID.fromString("b0e940b7-c937-42ae-9417-816dbe0394a5");
        final UUID TEST_ROOM_ID_2 = UUID.fromString("9ed11b12-992f-45aa-b223-195c9b752e14");

        BookingDetails bookingDetails = hotelService.bookHotel(
                BookHotelCommand.builder()
                        .hotelId(TEST_HOTEL_ID)
                        .locationId(TEST_LOCATION_ID)
                        .checkInDate(LocalDate.parse("2024-04-16"))
                        .checkOutDate(LocalDate.parse("2024-04-21"))
                        .rooms(
                                Set.of(
                                        BookRoomCommand.builder()
                                                .quantity(2)
                                                .roomId(TEST_ROOM_ID_1)
                                                .build(),
                                        BookRoomCommand.builder()
                                                .quantity(1)
                                                .roomId(TEST_ROOM_ID_2)
                                                .build()
                                )
                        )
                        .build()
        );

        assertTrue(bookingDetailsRepository.existsById(bookingDetails.getId()));
    }


}
