package com.hrs.hotelbooking.infra.dto;

import com.hrs.hotelbooking.domain.model.BookedRoom;
import com.hrs.hotelbooking.domain.model.Hotel;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingDetailsDto {
    private UUID id;

    private String checkInDate;

    private String checkOutDate;

    private BigDecimal totalPrice;

    private String bookedAt;

    private String lastModifiedAt;

    private HotelDto hotel;

    private Set<BookedRoomDto> bookedRooms;
}
