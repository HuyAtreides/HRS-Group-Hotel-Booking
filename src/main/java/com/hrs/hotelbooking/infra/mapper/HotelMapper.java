package com.hrs.hotelbooking.infra.mapper;

import com.hrs.hotelbooking.domain.model.Hotel;
import com.hrs.hotelbooking.infra.dto.HotelDto;
import org.springframework.stereotype.Component;

@Component
public class HotelMapper implements ToDtoMapper<Hotel, HotelDto>{

    @Override
    public HotelDto mapToDto(Hotel model) {
        return HotelDto.builder()
                .description(model.getDescription())
                .id(model.getId())
                .imageURL(model.getImageURL())
                .name(model.getName())
                .build();
    }
}
