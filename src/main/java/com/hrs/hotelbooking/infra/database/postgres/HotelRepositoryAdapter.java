package com.hrs.hotelbooking.infra.database.postgres;

import com.hrs.hotelbooking.application.Page;
import com.hrs.hotelbooking.application.query.PageOptions;
import com.hrs.hotelbooking.application.query.SearchHotelQuery;
import com.hrs.hotelbooking.application.repository.HotelRepository;
import com.hrs.hotelbooking.domain.model.Hotel;
import com.hrs.hotelbooking.domain.model.Room;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class HotelRepositoryAdapter implements HotelRepository {

    private final SpringHotelRepository hotelRepository;

    @Override
    public Page<Hotel> searchByCity(SearchHotelQuery searchHotelQuery) {
        PageOptions pageOptions = searchHotelQuery.getPageOptions();
        org.springframework.data.domain.Page<Hotel> hotels = hotelRepository.searchByCity(
                searchHotelQuery.getSearchTermCity(),
                PageRequest.of(
                        pageOptions.getPage(),
                        pageOptions.getSize()
                )
        );

        return Page.<Hotel>builder()
                .totalElements(hotels.getNumberOfElements())
                .totalPages(hotels.getTotalPages())
                .isLast(hotels.isLast())
                .content(hotels.getContent())
                .build();
    }
    @Override
    public Hotel findById(UUID id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Hotel not found"));
    }
}
