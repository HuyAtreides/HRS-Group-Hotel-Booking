package com.hrs.hotelbooking.infra.mapper;

import com.hrs.hotelbooking.domain.model.Room;
import com.hrs.hotelbooking.infra.dto.RoomDto;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper implements ToDtoMapper<Room, RoomDto> {
    @Override
    public RoomDto mapToDto(Room model) {
        return RoomDto.builder()
                .id(model.getId())
                .availability(model.getAvailability())
                .imageURL(model.getImageURL())
                .numberOfGuests(model.getNumberOfGuests())
                .price(model.getPrice())
                .type(model.getType())
                .build();
    }
}
