package com.hrs.hotelbooking.infra.mapper;

import com.hrs.hotelbooking.application.command.BookHotelCommand;
import com.hrs.hotelbooking.application.command.BookRoomCommand;
import com.hrs.hotelbooking.infra.dto.BookHotelCommandDto;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

@Component
public class BookHotelCommandMapper implements FromDtoMapper<BookHotelCommand, BookHotelCommandDto>{

    @Override
    public BookHotelCommand mapFromDto(BookHotelCommandDto dto) {
        return BookHotelCommand.builder()
                .checkInDate(LocalDate.parse(dto.getCheckInDate()))
                .checkOutDate(LocalDate.parse(dto.getCheckOutDate()))
                .rooms(dto.getRooms().stream().map(
                        bookRoomCommandDto -> BookRoomCommand.builder()
                                .roomId(bookRoomCommandDto.getRoomId())
                                .quantity(bookRoomCommandDto.getQuantity())
                                .build()
                ).collect(Collectors.toSet()))
                .build();
    }
}
