package com.hrs.hotelbooking.infra.mapper;

import com.hrs.hotelbooking.application.query.SearchHotelQuery;
import com.hrs.hotelbooking.domain.model.Hotel;
import com.hrs.hotelbooking.infra.dto.HotelDto;
import com.hrs.hotelbooking.infra.dto.SearchHotelQueryDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SearchHotelQueryMapper implements FromDtoMapper<SearchHotelQuery, SearchHotelQueryDto> {
    private final PageOptionsMapper pageOptionsMapper;

    @Override
    public SearchHotelQuery mapFromDto(SearchHotelQueryDto dto) {
        return SearchHotelQuery.builder()
                .pageOptions(pageOptionsMapper.mapFromDto(dto.getPageOptions()))
                .searchTermCity(dto.getSearchTerm())
                .build();
    }
}
