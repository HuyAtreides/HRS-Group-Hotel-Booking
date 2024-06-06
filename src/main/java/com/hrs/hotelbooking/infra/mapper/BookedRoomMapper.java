package com.hrs.hotelbooking.infra.mapper;

import com.hrs.hotelbooking.domain.model.BookedRoom;
import com.hrs.hotelbooking.infra.dto.BookedRoomDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BookedRoomMapper implements ToDtoMapper<BookedRoom, BookedRoomDto> {
    private final RoomMapper roomMapper;
    @Override
    public BookedRoomDto mapToDto(BookedRoom model) {
        return BookedRoomDto.builder()
                .id(model.getId())
                .quantity(model.getQuantity())
                .room(roomMapper.mapToDto(model.getRoom()))
                .build();
    }
}
