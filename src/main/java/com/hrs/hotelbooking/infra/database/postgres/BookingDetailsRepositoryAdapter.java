package com.hrs.hotelbooking.infra.database.postgres;

import com.hrs.hotelbooking.application.command.BookHotelCommand;
import com.hrs.hotelbooking.application.command.BookRoomCommand;
import com.hrs.hotelbooking.application.repository.BookingDetailsRepository;
import com.hrs.hotelbooking.domain.model.BookingDetails;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class BookingDetailsRepositoryAdapter implements BookingDetailsRepository {
    private final SpringBookingDetailsRepository bookingDetailsRepository;

    @Override
    public Set<BookingDetails> findAllOverlappingBookings(BookHotelCommand bookHotelCommand) {
        return bookingDetailsRepository.findAllOverlappingBookings(
                bookHotelCommand.getCheckInDate(),
                bookHotelCommand.getCheckOutDate(),
                bookHotelCommand.getRooms()
                        .stream().map(BookRoomCommand::getRoomId)
                        .collect(Collectors.toSet())
        );
    }

    @Override
    public BookingDetails save(BookingDetails bookingDetails) {
        return bookingDetailsRepository.save(bookingDetails);
    }
}
