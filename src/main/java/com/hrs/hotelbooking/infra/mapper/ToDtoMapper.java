package com.hrs.hotelbooking.infra.mapper;

public interface ToDtoMapper<DomainType, DtoType> {
    DtoType mapToDto(DomainType model);
}
