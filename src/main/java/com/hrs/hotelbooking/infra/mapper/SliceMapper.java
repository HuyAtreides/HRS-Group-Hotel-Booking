package com.hrs.hotelbooking.infra.mapper;


import com.hrs.hotelbooking.application.Slice;
import com.hrs.hotelbooking.infra.dto.SliceDto;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class SliceMapper<DomainType, DtoType> {

    SliceDto<DtoType> mapToDto(Slice<DomainType> slice, ToDtoMapper<DomainType, DtoType> contentMapper) {
        return SliceDto.<DtoType>builder()
                .isLast(slice.isLast())
                .page(slice.getPage())
                .content(slice.getContent()
                        .stream()
                        .map(contentMapper::mapToDto)
                        .collect(Collectors.toList())
                )
                .build();
    }
}
