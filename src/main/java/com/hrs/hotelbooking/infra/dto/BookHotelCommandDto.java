package com.hrs.hotelbooking.infra.dto;

import java.util.Set;
import lombok.Data;

@Data
public class BookHotelCommandDto {
    private Set<BookRoomCommandDto> rooms;
    private String checkInDate;
    private String checkOutDate;
}
