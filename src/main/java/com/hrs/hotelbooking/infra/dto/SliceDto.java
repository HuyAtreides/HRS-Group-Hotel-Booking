package com.hrs.hotelbooking.infra.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class SliceDto<ContentType> {
    private int page;

    private boolean isLast;

    private List<ContentType> content;
}
