package com.hrs.hotelbooking.infra.dto;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BookRoomCommandDto {
    private int quantity;

    private UUID roomId;
}
