package com.hrs.hotelbooking.integration;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.hrs.hotelbooking.application.AuthenticatedUserHolderService;
import com.hrs.hotelbooking.application.CurrentDateTimeService;
import com.hrs.hotelbooking.application.HotelService;
import com.hrs.hotelbooking.application.command.BookingModificationCommand;
import com.hrs.hotelbooking.application.repository.BookingDetailsRepository;
import com.hrs.hotelbooking.domain.model.BookingDetails;
import com.hrs.hotelbooking.domain.model.User;
import com.hrs.hotelbooking.infra.database.postgres.SpringBookingDetailsRepository;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cglib.core.Local;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@IntegrationTest
@Sql(value = "classpath:db/script/populate-booking-details.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "classpath:db/script/clean-booking-details.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
public class ModifyBookingIntegrationTest {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private BookingDetailsRepository bookingDetailsRepository;

    @Test
    void valid_booking_modification_should_result_in_booking_updated_correctly() {
        UUID bookingId = UUID.fromString("9a6e6a50-c6d6-4f88-a9aa-70338e4af401");
        LocalDate newCheckInDate = LocalDate.parse("2024-03-16");

        hotelService.modifyBooking(
                BookingModificationCommand.builder()
                        .existingBookingId(bookingId)
                        .newCheckInDate(newCheckInDate)
                        .build()
        );

        BookingDetails bookingDetails = bookingDetailsRepository.findById(bookingId);

        assertAll("Booking Details Updated Correctly",
                () -> assertEquals(newCheckInDate, bookingDetails.getCheckInDate()),
                () -> assertEquals(
                        0,
                        bookingDetails.getTotalPrice().compareTo(BigDecimal.valueOf(7977.3))
                )
        );
    }
}
