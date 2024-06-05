package com.hrs.hotelbooking.application;

import com.hrs.hotelbooking.application.query.SearchHotelQuery;
import com.hrs.hotelbooking.application.repository.HotelRepository;
import com.hrs.hotelbooking.domain.model.Hotel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;
    public Page<Hotel> searchHotels(SearchHotelQuery query) {
        return hotelRepository.searchByCity(query);
    }
}
