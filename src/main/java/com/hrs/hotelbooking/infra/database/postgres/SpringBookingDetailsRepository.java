package com.hrs.hotelbooking.infra.database.postgres;

import com.hrs.hotelbooking.domain.model.BookingDetails;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SpringBookingDetailsRepository extends CrudRepository<BookingDetails, UUID> {

    @Query("""
            select bookingDetails
            from BookingDetails bookingDetails inner join bookingDetails.bookedRooms bookedRoom
            where (
                    bookingDetails.checkInDate <= :checkOutDate 
                    and :checkInDate <= bookingDetails.checkOutDate
                  )
                  and bookedRoom.room.id in :roomIds
            """)
    Set<BookingDetails> findAllOverlappingBookings(
            @Param("checkInDate") LocalDate checkInDate,
            @Param("checkOutDate") LocalDate checkOutDate,
            @Param("roomIds") Set<UUID> roomIds
    );

    @Query("""
            select bookingDetails
            from BookingDetails bookingDetails inner join bookingDetails.bookedRooms bookedRoom
            where (
                    bookingDetails.checkInDate <= :checkOutDate 
                    and :checkInDate <= bookingDetails.checkOutDate
                  )
                  and bookingDetails.id <> :existingBookingId
                  and bookedRoom.room.id in :roomIds
            """)
    Set<BookingDetails> findAllOverlappingBookings(
            @Param("existingBookingId") UUID existingBookingId,
            @Param("checkInDate") LocalDate checkInDate,
            @Param("checkOutDate") LocalDate checkOutDate,
            @Param("roomIds") Set<UUID> roomIds
    );
}
