package com.hrs.hotelbooking.infra.dto;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookedRoomDto {
    private UUID id;

    private int quantity;

    private RoomDto room;
}
