package com.hrs.hotelbooking.infra.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SearchHotelQueryDto {
    private PageOptionsDto pageOptions;

    private String searchTerm;
}
