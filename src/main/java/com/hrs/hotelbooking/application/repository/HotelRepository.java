package com.hrs.hotelbooking.application.repository;

import com.hrs.hotelbooking.application.Page;
import com.hrs.hotelbooking.application.query.SearchHotelQuery;
import com.hrs.hotelbooking.domain.model.Hotel;
import org.springframework.data.domain.Pageable;

public interface HotelRepository {
    Page<Hotel> searchByCity(SearchHotelQuery searchHotelQuery);
}
