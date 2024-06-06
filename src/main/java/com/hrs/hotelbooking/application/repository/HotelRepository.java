package com.hrs.hotelbooking.application.repository;

import com.hrs.hotelbooking.application.Page;
import com.hrs.hotelbooking.application.query.SearchHotelQuery;
import com.hrs.hotelbooking.domain.model.Hotel;
import com.hrs.hotelbooking.domain.model.Room;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.domain.Pageable;

public interface HotelRepository {
    Page<Hotel> searchByCity(SearchHotelQuery searchHotelQuery);

    Hotel findById(UUID id);
}
