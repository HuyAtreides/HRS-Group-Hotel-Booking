package com.hrs.hotelbooking.infra.dto;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HotelDto {
    private UUID id;

    private String name;

    private String description;

    private String imageURL;
}
