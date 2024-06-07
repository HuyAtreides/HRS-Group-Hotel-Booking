package com.hrs.hotelbooking.infra.rest;

import com.hrs.hotelbooking.domain.exception.InvalidBooking;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleException(Exception exception) {
        return ApiError.builder()
                .errorMessage(exception.getMessage())
                .build();
    }
}
