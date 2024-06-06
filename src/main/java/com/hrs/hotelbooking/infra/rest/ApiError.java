package com.hrs.hotelbooking.infra.rest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiError {
    private String errorMessage;
}
