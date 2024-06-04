package com.hrs.hotelbooking.infra.mapper;

public interface FromDtoMapper<DomainType, DtoType> {
    DomainType mapFromDto(DtoType dto);
}
