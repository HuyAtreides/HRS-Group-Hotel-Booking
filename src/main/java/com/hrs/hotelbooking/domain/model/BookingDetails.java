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
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.Local;

@Entity
@Getter
@NoArgsConstructor
@Builder
@Slf4j
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

    @OneToMany(mappedBy = "bookingDetails", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<BookedRoom> bookedRooms;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User owner;

    public BookingDetails(UUID id, LocalDate checkInDate, LocalDate checkOutDate,
            BigDecimal ignoredTotalPrice,
            Instant bookedAt, Instant lastModifiedAt, Hotel hotel, Set<BookedRoom> bookedRooms,
            User owner
    ) {
        if (checkInDate.isBefore(LocalDate.ofInstant(bookedAt, ZoneOffset.UTC))) {
            throw new InvalidBooking("Check in date in the past");
        }

        validateCheckInCheckOutDate(checkInDate, checkOutDate);

        if (bookedRooms.isEmpty()) {
            throw new InvalidBooking("Booked rooms list is empty");
        }

        this.id = id;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.bookedAt = bookedAt;
        this.lastModifiedAt = lastModifiedAt;
        this.hotel = hotel;
        this.bookedRooms = bookedRooms;
        this.owner = owner;
        this.totalPrice = calculateTotalPrice();

        bookedRooms.forEach(bookedRoom -> bookedRoom.associateWithBookingDetails(this));
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

    private void validateIfModifierIsOwner(User modifier) {
        if (!this.isOwnedBy(modifier)) {
            throw new InvalidBookingModification("Modifier is not the booking owner");
        }
    }

    private void validateIfDaysBetweenModifiedDateAndBookedDateAcceptable(LocalDate modifiedDate) {
        LocalDate bookedAtDate = LocalDate.ofInstant(bookedAt, ZoneOffset.UTC);

        if (ChronoUnit.DAYS.between(bookedAtDate, modifiedDate) > 2) {
            throw new InvalidBookingModification(
                    "Can only modify within at most 2 days after booked date"
            );
        }
    }

    private void validateIfModifiedDateAfterCheckInDate(LocalDate modificationDate) {
        if (modificationDate.isAfter(this.checkInDate) || modificationDate.equals(
                this.checkInDate)) {
            throw new InvalidBookingModification("Modification date can not be after or equal to check in date");
        }
    }

    public void validateIfEligibleForCancellation(CancelBookingRequest request) {
        User modifier = request.getModifier();
        LocalDate cancelDate = LocalDate.ofInstant(request.getCancelAt(), ZoneOffset.UTC);

        validateIfModifierIsOwner(modifier);
        validateIfModifiedDateAfterCheckInDate(cancelDate);
        validateIfDaysBetweenModifiedDateAndBookedDateAcceptable(cancelDate);
    }
    public void validateIfEligibleForModification(BookingModificationRequest request) {
        User modifier = request.getModifier();
        LocalDate newCheckInDate = request.getNewCheckInDate();
        LocalDate newCheckOutDate = request.getNewCheckOutDate();
        LocalDate modificationDate = LocalDate.ofInstant(request.getModifiedAt(), ZoneOffset.UTC);

        validateIfModifierIsOwner(modifier);
        validateIfModifiedDateAfterCheckInDate(modificationDate);
        validateIfDaysBetweenModifiedDateAndBookedDateAcceptable(modificationDate);

        boolean stayingPeriodUnchanged =
                newCheckInDate != null && newCheckInDate.equals(this.checkInDate) &&
                        newCheckOutDate != null && newCheckOutDate.equals(this.checkOutDate);

        if (stayingPeriodUnchanged) {
            throw new InvalidBookingModification("Nothing changed");
        }
    }

    public void modify(BookingModificationRequest request) {
        LocalDate newCheckInDate = request.getNewCheckInDate();
        LocalDate newCheckOutDate = request.getNewCheckOutDate();

        if (newCheckInDate != null) {
            this.checkInDate = newCheckInDate;
        }

        if (newCheckOutDate != null) {
            this.checkOutDate = newCheckOutDate;
        }

        validateCheckInCheckOutDate(this.checkInDate, this.checkOutDate);
        this.totalPrice = calculateTotalPrice();
        this.lastModifiedAt = request.getModifiedAt();
    }

    private BigDecimal calculateTotalPrice() {
        BigDecimal days = BigDecimal.valueOf(ChronoUnit.DAYS.between(checkInDate, checkOutDate));

        return bookedRooms.stream().reduce(
                BigDecimal.ZERO,
                (subtotal, bookingRoom) -> {
                    BigDecimal roomPriceInDays = bookingRoom.getRoom().getPrice().multiply(days);
                    BigDecimal numberOfRooms = BigDecimal.valueOf(bookingRoom.getQuantity());

                    return subtotal.add(roomPriceInDays.multiply(numberOfRooms));
                },
                BigDecimal::add
        );
    }
}
