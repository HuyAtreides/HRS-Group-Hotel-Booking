package com.hrs.hotelbooking.infra.mapper;

import com.hrs.hotelbooking.application.Page;
import com.hrs.hotelbooking.application.Slice;
import com.hrs.hotelbooking.infra.dto.PageDto;
import com.hrs.hotelbooking.infra.dto.SliceDto;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class PageMapper<DomainType, DtoType> {
    public PageDto<DtoType> mapToDto(Page<DomainType> page, ToDtoMapper<DomainType, DtoType> contentMapper) {
        return PageDto.<DtoType>builder()
                .isLast(page.isLast())
                .page(page.getPage())
                .content(page.getContent()
                        .stream()
                        .map(contentMapper::mapToDto)
                        .collect(Collectors.toList())
                )
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();
    }
}
