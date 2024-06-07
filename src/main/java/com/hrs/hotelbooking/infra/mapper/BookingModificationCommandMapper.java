package com.hrs.hotelbooking.infra.mapper;

import com.hrs.hotelbooking.application.command.BookingModificationCommand;
import com.hrs.hotelbooking.infra.dto.BookingModificationCommandDto;
import java.time.LocalDate;
import org.springframework.stereotype.Component;

@Component
public class BookingModificationCommandMapper implements FromDtoMapper<BookingModificationCommand, BookingModificationCommandDto>{

    @Override
    public BookingModificationCommand mapFromDto(BookingModificationCommandDto dto) {
        return BookingModificationCommand.builder()
                .newCheckInDate(LocalDate.parse(dto.getNewCheckInDate()))
                .newCheckOutDate(LocalDate.parse(dto.getNewCheckOutDate()))
                .build();
    }
}
