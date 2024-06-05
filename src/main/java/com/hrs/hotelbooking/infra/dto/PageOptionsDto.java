package com.hrs.hotelbooking.infra.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageOptionsDto {
    private int page;
    private int size;
}
