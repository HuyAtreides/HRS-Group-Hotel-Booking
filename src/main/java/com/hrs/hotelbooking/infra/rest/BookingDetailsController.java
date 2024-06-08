package com.hrs.hotelbooking.infra.rest;

import com.hrs.hotelbooking.application.BookingDetailsService;
import com.hrs.hotelbooking.application.Page;
import com.hrs.hotelbooking.application.query.PageOptions;
import com.hrs.hotelbooking.domain.model.BookingDetails;
import com.hrs.hotelbooking.infra.dto.BookingDetailsDto;
import com.hrs.hotelbooking.infra.dto.PageDto;
import com.hrs.hotelbooking.infra.dto.PageOptionsDto;
import com.hrs.hotelbooking.infra.mapper.BookingDetailsMapper;
import com.hrs.hotelbooking.infra.mapper.PageMapper;
import com.hrs.hotelbooking.infra.mapper.PageOptionsMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking-details")
@AllArgsConstructor
public class BookingDetailsController {
    private final BookingDetailsService bookingDetailsService;
    private final BookingDetailsMapper bookingDetailsMapper;
    private final PageMapper<BookingDetails, BookingDetailsDto> pageMapper;
    private final PageOptionsMapper pageOptionsMapper;

    @GetMapping
    public PageDto<BookingDetailsDto> getBookingDetails(
            PageOptionsDto pageOptionsDto
    ) {
        PageOptions pageOptions = pageOptionsMapper.mapFromDto(pageOptionsDto);
        Page<BookingDetails> bookingDetails = bookingDetailsService.findAllBookingDetailsOfUser(
                pageOptions
        );

        return pageMapper.mapToDto(bookingDetails, bookingDetailsMapper);
    }
}
