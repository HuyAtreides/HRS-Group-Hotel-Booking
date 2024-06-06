package com.hrs.hotelbooking.domain.model;

import com.hrs.hotelbooking.domain.exception.InvalidBooking;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Set;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Builder
public class BookingDetails {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "check_in_date")
    private LocalDate checkInDate;

    @Column(name = "check_out_date")
    private LocalDate checkOutDate;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "booked_at")
    private Instant bookedAt;

    @Column(name = "last_modified_at")
    private Instant lastModifiedAt;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @OneToMany(mappedBy = "bookingDetails")
    private Set<BookedRoom> bookedRooms;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User owner;

    public BookingDetails(UUID id, LocalDate checkInDate, LocalDate checkOutDate, BigDecimal totalPrice,
            Instant bookedAt, Instant lastModifiedAt, Hotel hotel, Set<BookedRoom> bookedRooms,
            User owner
    ) {
        if (checkInDate.isBefore(LocalDate.ofInstant(bookedAt, ZoneOffset.UTC))) {
            throw new InvalidBooking("Check in date in the past");
        }

        if (checkInDate.isAfter(checkOutDate)) {
            throw new InvalidBooking("Check in date is after check out date");
        }

        this.id = id;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalPrice = totalPrice;
        this.bookedAt = bookedAt;
        this.lastModifiedAt = lastModifiedAt;
        this.hotel = hotel;
        this.bookedRooms = bookedRooms;
        this.owner = owner;
    }
}
