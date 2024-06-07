package com.hrs.hotelbooking.domain.model;

import com.hrs.hotelbooking.domain.exception.InvalidBooking;
import com.hrs.hotelbooking.domain.exception.InvalidBookingModification;
import jakarta.persistence.CascadeType;
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
import java.time.temporal.ChronoUnit;
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

    @OneToMany(mappedBy = "bookingDetails", cascade = CascadeType.PERSIST)
    private Set<BookedRoom> bookedRooms;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User owner;

    public BookingDetails(UUID id, LocalDate checkInDate, LocalDate checkOutDate,
            BigDecimal totalPrice,
            Instant bookedAt, Instant lastModifiedAt, Hotel hotel, Set<BookedRoom> bookedRooms,
            User owner
    ) {
        if (checkInDate.isBefore(getBookedDate())) {
            throw new InvalidBooking("Check in date in the past");
        }

        validateCheckInCheckOutDate(checkInDate, checkOutDate);

        if (bookedRooms.isEmpty()) {
            throw new InvalidBooking("Booked rooms list is empty");
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

        bookedRooms.forEach(bookedRoom -> bookedRoom.associateWithBookingDetails(this));
    }

    private LocalDate getBookedDate() {
        return LocalDate.ofInstant(bookedAt, ZoneOffset.UTC);
    }
    private void validateCheckInCheckOutDate(LocalDate checkInDate, LocalDate checkOutDate) {
        if (checkInDate.isAfter(checkOutDate)) {
            throw new InvalidBooking("Check in date is after check out date");
        }

        if (checkInDate.equals(checkOutDate)) {
            throw new InvalidBooking("Check in date can not equal check out date");
        }
    }

    private boolean isOwnedBy(User user) {
        return owner.equals(user);
    }

    public void validateIfEligibleForModification(BookingModificationRequest request) {
        User modifier = request.getModifier();
        LocalDate modificationDate = LocalDate.ofInstant(request.getModifiedAt(), ZoneOffset.UTC);

        if (!this.isOwnedBy(modifier)) {
            throw new InvalidBookingModification("Modifier is not the booking owner");
        }

        if (modificationDate.isAfter(this.checkInDate)) {
            throw new InvalidBookingModification("Modification can not be after check in date");
        }

        if (ChronoUnit.DAYS.between(modificationDate, bookedAt) > 2) {
            throw new InvalidBookingModification("Can only modify within at most 2 days after booked date");
        }
    }

    public void applyModification(BookingModificationRequest request) {
        LocalDate newCheckInDate = request.getNewCheckInDate();
        LocalDate newCheckOutDate = request.getNewCheckOutDate();
        validateCheckInCheckOutDate(newCheckInDate, newCheckOutDate);
        this.checkInDate = newCheckInDate;
        this.checkOutDate = newCheckOutDate;
        this.lastModifiedAt = request.getModifiedAt();
    }
}
