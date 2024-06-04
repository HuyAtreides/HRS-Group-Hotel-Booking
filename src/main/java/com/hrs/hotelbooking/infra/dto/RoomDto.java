package com.hrs.hotelbooking.infra.dto;

import com.hrs.hotelbooking.domain.model.Location;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomDto {
    private UUID id;

    private int availability;

    private String type;

    private BigDecimal price;

    private String imageURL;

    private int numberOfGuests;
}
