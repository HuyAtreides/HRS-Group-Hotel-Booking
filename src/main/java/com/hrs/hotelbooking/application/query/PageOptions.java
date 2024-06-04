package com.hrs.hotelbooking.application.query;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageOptions {
    private int page;
    private int size;
}
