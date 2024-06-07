package com.hrs.hotelbooking.infra.dto;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingModificationCommandDto {
    private String newCheckInDate;
    private String newCheckOutDate;
}
