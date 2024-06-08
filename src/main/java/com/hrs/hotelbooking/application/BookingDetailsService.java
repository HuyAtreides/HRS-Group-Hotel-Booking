package com.hrs.hotelbooking.application;

import com.hrs.hotelbooking.application.query.PageOptions;
import com.hrs.hotelbooking.application.repository.BookingDetailsRepository;
import com.hrs.hotelbooking.domain.model.BookingDetails;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookingDetailsService {
    private final BookingDetailsRepository bookingDetailsRepository;
    private final AuthenticatedUserHolderService authenticatedUserHolderService;
    public Page<BookingDetails> findAllBookingDetailsOfUser(PageOptions pageOptions) {
        return bookingDetailsRepository.findAllBookingDetailsOfUser(
                authenticatedUserHolderService.getAuthenticatedUser(),
                pageOptions
        );
    }
}
