package com.hrs.hotelbooking.infra.rest;

import com.hrs.hotelbooking.application.HotelService;
import com.hrs.hotelbooking.application.Page;
import com.hrs.hotelbooking.application.command.BookHotelCommand;
import com.hrs.hotelbooking.domain.model.BookingDetails;
import com.hrs.hotelbooking.domain.model.Hotel;
import com.hrs.hotelbooking.infra.dto.BookHotelCommandDto;
import com.hrs.hotelbooking.infra.dto.BookingDetailsDto;
import com.hrs.hotelbooking.infra.dto.HotelDto;
import com.hrs.hotelbooking.infra.dto.PageDto;
import com.hrs.hotelbooking.infra.dto.SearchHotelQueryDto;
import com.hrs.hotelbooking.infra.mapper.BookHotelCommandMapper;
import com.hrs.hotelbooking.infra.mapper.BookingDetailsMapper;
import com.hrs.hotelbooking.infra.mapper.HotelMapper;
import com.hrs.hotelbooking.infra.mapper.PageMapper;
import com.hrs.hotelbooking.infra.mapper.SearchHotelQueryMapper;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hotel")
@AllArgsConstructor
public class HotelController {
    private final HotelService hotelService;
    private final HotelMapper hotelMapper;
    private final PageMapper<Hotel, HotelDto> pageMapper;
    private final SearchHotelQueryMapper searchHotelQueryMapper;
    private final BookHotelCommandMapper bookHotelCommandMapper;
    private final BookingDetailsMapper bookingDetailsMapper;

    @PostMapping("/search")
    public PageDto<HotelDto> searchHotel(
            @RequestBody SearchHotelQueryDto searchHotelQueryDto
    ) {
        Page<Hotel> pageOfHotel = hotelService.searchHotels(
                searchHotelQueryMapper.mapFromDto(searchHotelQueryDto)
        );

        return pageMapper.mapToDto(pageOfHotel, hotelMapper);
    }

    @PostMapping("/{hotelId}/{locationId}/book")
    public BookingDetailsDto bookHotel(
            @PathVariable String hotelId,
            @PathVariable String locationId,
            @RequestBody BookHotelCommandDto bookHotelCommandDto
    ) {
        BookHotelCommand bookHotelCommand = bookHotelCommandMapper.mapFromDto(bookHotelCommandDto);
        bookHotelCommand.setHotelId(UUID.fromString(hotelId));
        bookHotelCommand.setLocationId(UUID.fromString(locationId));

        return bookingDetailsMapper.mapToDto(
          hotelService.bookHotel(bookHotelCommand)
        );
    }
}
