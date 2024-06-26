package com.hrs.hotelbooking.domain.model;

import com.hrs.hotelbooking.domain.exception.InvalidBooking;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hotel", schema = "public")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hotel {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image_url")
    private String imageURL;

    @OneToMany(mappedBy = "hotel")
    private Set<Location> locations;

    public Location findLocationById(UUID id) {
        return locations.stream()
                .filter(location -> location.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Location ID doesn't exist"));
    }

    private Set<Room> getRoomsInLocation(Location bookingLocation) {
        return locations.stream()
                .filter(location -> location.equals(bookingLocation))
                .findFirst()
                .orElseThrow(() -> new InvalidBooking("This hotel doesn't have this location"))
                .getRooms();
    }

    private boolean areAllBookingRoomsQuantityCanBeSatisfied(
            Set<BookedRoom> bookingRooms,
            Set<BookingDetails> overlappingBookings
    ) {
        Map<UUID, Integer> bookingRoomIdToQuantityMap = bookingRooms.stream().collect(
                Collectors.toMap(
                        bookedRoom -> bookedRoom.getRoom().getId(),
                        BookedRoom::getQuantity
                )
        );
        Map<UUID, Integer> bookingRoomIdToAvailabilityMap = bookingRooms.stream().collect(
                Collectors.toMap(
                        bookedRoom -> bookedRoom.getRoom().getId(),
                        bookedRoom -> bookedRoom.getRoom().getAvailability()
                )
        );

        for (BookingDetails bookingDetails : overlappingBookings) {
            for (BookedRoom bookedRoom : bookingDetails.getBookedRooms()) {
                UUID bookedRoomId = bookedRoom.getRoom().getId();
                int bookedRoomQuantity = bookedRoom.getQuantity();
                boolean bookedRoomInBookingRooms = bookingRoomIdToAvailabilityMap.containsKey(bookedRoomId);

                if (!bookedRoomInBookingRooms) {
                    continue;
                }

                int bookingRoomAvailability = bookingRoomIdToAvailabilityMap.get(bookedRoomId) - bookedRoomQuantity;
                int bookingRoomQuantity = bookingRoomIdToQuantityMap.get(bookedRoomId);

                if (bookingRoomQuantity > bookingRoomAvailability) {
                    return false;
                }

                bookingRoomIdToAvailabilityMap.put(
                        bookedRoomId,
                        bookingRoomAvailability
                );
            }
        }

        return true;
    }

    private void validateBookingRoomsQuantity(
            Set<BookedRoom> bookingRooms,
            Set<BookingDetails> overlappingBooking
    ) {
        if (!areAllBookingRoomsQuantityCanBeSatisfied(bookingRooms, overlappingBooking)) {
            throw new InvalidBooking("One of the booking rooms quantity can not be satisfied");
        }
    }

    private void validateBookingRoomsBelongToHotelLocation(
            Set<BookedRoom> bookingRooms,
            Location location
    ) {
        Set<Room> hotelRooms = getRoomsInLocation(location);
        Set<Room> rooms = bookingRooms.stream().map(BookedRoom::getRoom)
                .collect(Collectors.toSet());
        boolean allBookingRoomsInHotelRooms = hotelRooms.containsAll(rooms);

        if (!allBookingRoomsInHotelRooms) {
            throw new InvalidBooking("One of the booking rooms is not in hotel");
        }
    }

    public void modifyBooking(
            BookingModificationRequest bookingModificationRequest,
            Set<BookingDetails> overlappingBooking
    ) {
        BookingDetails existingBooking = bookingModificationRequest.getBookingDetails();
        existingBooking.validateIfEligibleForModification(bookingModificationRequest);

        validateBookingRoomsQuantity(existingBooking.getBookedRooms() , overlappingBooking);
        existingBooking.modify(bookingModificationRequest);
    }

    public void cancelBooking(CancelBookingRequest cancelBookingRequest) {
        BookingDetails bookingDetails = cancelBookingRequest.getBookingDetails();
        bookingDetails.validateIfEligibleForCancellation(cancelBookingRequest);
    }

    public BookingDetails book(BookingRequest request, Set<BookingDetails> overlappingBooking) {
        Set<BookedRoom> bookingRooms = request.getRooms();
        validateBookingRoomsBelongToHotelLocation(bookingRooms, request.getLocation());
        validateBookingRoomsQuantity(bookingRooms , overlappingBooking);

        return BookingDetails.builder()
                .checkInDate(request.getCheckInDate())
                .checkOutDate(request.getCheckOutDate())
                .bookedRooms(bookingRooms)
                .bookedAt(request.getBookedAt())
                .lastModifiedAt(request.getBookedAt())
                .hotel(this)
                .owner(request.getUser())
                .build();
    }
}
