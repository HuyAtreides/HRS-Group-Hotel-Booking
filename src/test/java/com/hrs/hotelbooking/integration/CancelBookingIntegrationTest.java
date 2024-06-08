package com.hrs.hotelbooking.integration;

import static org.junit.jupiter.api.Assertions.assertFalse;

import com.hrs.hotelbooking.application.HotelService;
import com.hrs.hotelbooking.application.command.CancelBookingCommand;
import com.hrs.hotelbooking.infra.database.postgres.SpringBookingDetailsRepository;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@IntegrationTest
@Sql(value = "classpath:db/script/populate-booking-details.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "classpath:db/script/clean-booking-details.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
public class CancelBookingIntegrationTest {
    @Autowired
    private HotelService hotelService;

    @Autowired
    private SpringBookingDetailsRepository bookingDetailsRepository;

    @Test
    void valid_booking_cancellation_result_in_the_booking_deleted() {
        UUID id = UUID.fromString("9a6e6a50-c6d6-4f88-a9aa-70338e4af401");

        hotelService.cancelBooking(
                CancelBookingCommand.builder()
                        .bookingDetailsId(id)
                        .build()
        );

        assertFalse(bookingDetailsRepository.existsById(id));
    }
}
