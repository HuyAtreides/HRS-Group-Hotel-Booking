package com.hrs.hotelbooking.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Builder
public class BookedRoom {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "booking_details_id")
    private BookingDetails bookingDetails;

    @OneToOne
    @JoinColumn(name = "room_id")
    private Room room;

    public BookedRoom(UUID id, int quantity, BookingDetails bookingDetails, Room room) {
        if (quantity > room.getAvailability()) {
            throw new IllegalArgumentException("Booked quantity can not greater than room availability");
        }

        this.id = id;
        this.quantity = quantity;
        this.bookingDetails = bookingDetails;
        this.room = room;
    }
}
