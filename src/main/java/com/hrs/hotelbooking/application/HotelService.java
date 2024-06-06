package com.hrs.hotelbooking.application;

import com.hrs.hotelbooking.application.command.BookHotelCommand;
import com.hrs.hotelbooking.application.command.BookRoomCommand;
import com.hrs.hotelbooking.application.query.SearchHotelQuery;
import com.hrs.hotelbooking.application.repository.BookingDetailsRepository;
import com.hrs.hotelbooking.application.repository.HotelRepository;
import com.hrs.hotelbooking.application.repository.RoomRepository;
import com.hrs.hotelbooking.domain.model.BookedRoom;
import com.hrs.hotelbooking.domain.model.BookingDetails;
import com.hrs.hotelbooking.domain.model.BookingRequest;
import com.hrs.hotelbooking.domain.model.Hotel;
import com.hrs.hotelbooking.domain.model.Room;
import com.hrs.hotelbooking.domain.model.User;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class HotelService {

    private final HotelRepository hotelRepository;
    private final BookingDetailsRepository bookingDetailsRepository;
    private final RoomRepository roomRepository;
    private final CurrentDateTimeService currentDateTimeService;
    private final AuthenticatedUserHolderService authenticatedUserHolderService;
    public Page<Hotel> searchHotels(SearchHotelQuery query) {
        return hotelRepository.searchByCity(query);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public BookingDetails bookHotel(BookHotelCommand bookHotelCommand) {
        User user = authenticatedUserHolderService.getAuthenticatedUser();
        log.info("message=book hotel, method=bookHotel, bookingInfo={}, user={}",
                bookHotelCommand,
                user
        );

        Hotel hotel = hotelRepository.findById(bookHotelCommand.getHotelId());
        Set<BookingDetails> overlappingBookingDetails = bookingDetailsRepository.findAllOverlappingBookings(
                bookHotelCommand
        );
        Set<UUID> bookingRoomIds = bookHotelCommand.getRooms()
                .stream()
                .map(BookRoomCommand::getRoomId)
                .collect(Collectors.toSet());
        Map<UUID, Room> idToRoomMap = roomRepository.findRoomsInIds(bookingRoomIds)
                .stream()
                .collect(Collectors.toMap(Room::getId, room -> room));

        BookingRequest bookingRequest = BookingRequest.builder()
                .bookedAt(currentDateTimeService.getCurrentDateTime())
                .location(hotel.findLocationById(bookHotelCommand.getLocationId()))
                .checkInDate(bookHotelCommand.getCheckInDate())
                .checkOutDate(bookHotelCommand.getCheckOutDate())
                .rooms(bookHotelCommand.getRooms().stream().map(
                        bookRoomCommand -> BookedRoom.builder()
                                .quantity(bookRoomCommand.getQuantity())
                                .room(idToRoomMap.get(bookRoomCommand.getRoomId()))
                                .build()
                ).collect(Collectors.toSet()))
                .user(user)
                .build();
        BookingDetails bookingDetails = hotel.book(bookingRequest, overlappingBookingDetails);

        return bookingDetailsRepository.save(bookingDetails);
    }
}
