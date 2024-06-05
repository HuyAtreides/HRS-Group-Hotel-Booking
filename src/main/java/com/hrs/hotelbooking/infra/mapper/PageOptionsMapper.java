package com.hrs.hotelbooking.infra.mapper;

import com.hrs.hotelbooking.application.query.PageOptions;
import com.hrs.hotelbooking.infra.dto.PageOptionsDto;
import org.springframework.stereotype.Component;

@Component
public class PageOptionsMapper implements FromDtoMapper<PageOptions, PageOptionsDto> {

    @Override
    public PageOptions mapFromDto(PageOptionsDto dto) {
        return PageOptions.builder()
                .page(dto.getPage())
                .size(dto.getSize())
                .build();
    }
}
