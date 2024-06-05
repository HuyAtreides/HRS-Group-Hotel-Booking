package com.hrs.hotelbooking.application;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Page<ContentType> extends Slice<ContentType> {
    private int totalPages;
    private int totalElements;
}
