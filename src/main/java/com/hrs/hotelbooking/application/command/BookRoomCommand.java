package com.hrs.hotelbooking.application.command;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookRoomCommand {
    private int quantity;

    private UUID roomId;
}
