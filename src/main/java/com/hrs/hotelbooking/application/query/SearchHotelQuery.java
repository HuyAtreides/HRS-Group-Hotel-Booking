package com.hrs.hotelbooking.application.query;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchHotelQuery {
    private PageOptions pageOptions;

    private String searchTermCity;
}
