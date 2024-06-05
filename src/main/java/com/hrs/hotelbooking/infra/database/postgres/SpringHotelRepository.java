package com.hrs.hotelbooking.infra.database.postgres;

import com.hrs.hotelbooking.domain.model.Hotel;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface SpringHotelRepository extends CrudRepository<Hotel, UUID> {
    @Query("""
            select hotel
            from Hotel hotel inner join hotel.locations location
            where lower(location.city) like concat('%', lower(:city), '%')
            """)
    Page<Hotel> searchByCity(@Param("city") String city, Pageable pageable);
}
