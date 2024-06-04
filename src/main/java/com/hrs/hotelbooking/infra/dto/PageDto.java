package com.hrs.hotelbooking.infra.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class PageDto<ContentType> extends SliceDto<ContentType> {
    private int totalPages;

    private int totalElements;
}
