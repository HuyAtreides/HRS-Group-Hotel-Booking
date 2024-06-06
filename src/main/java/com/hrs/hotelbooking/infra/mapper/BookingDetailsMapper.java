package com.hrs.hotelbooking.infra.mapper;

import com.hrs.hotelbooking.domain.model.BookingDetails;
import com.hrs.hotelbooking.infra.dto.BookingDetailsDto;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BookingDetailsMapper implements ToDtoMapper<BookingDetails, BookingDetailsDto> {

    private final HotelMapper hotelMapper;
    private final BookedRoomMapper bookedRoomMapper;

    @Override
    public BookingDetailsDto mapToDto(BookingDetails model) {
        return BookingDetailsDto.builder()
                .id(model.getId())
                .bookedAt(model.getBookedAt().toString())
                .checkInDate(model.getCheckInDate().toString())
                .checkOutDate(model.getCheckOutDate().toString())
                .totalPrice(model.getTotalPrice())
                .hotel(hotelMapper.mapToDto(model.getHotel()))
                .bookedRooms(model.getBookedRooms()
                        .stream().map(bookedRoomMapper::mapToDto)
                        .collect(Collectors.toSet())
                )
                .build();
    }
}
