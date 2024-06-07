package com.hrs.hotelbooking.stub;

import com.hrs.hotelbooking.application.AuthenticatedUserHolderService;
import com.hrs.hotelbooking.domain.model.User;
import java.util.UUID;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("test")
@Component
public class StubAuthenticatedUserService implements AuthenticatedUserHolderService {

    @Override
    public User getAuthenticatedUser() {
        return User.builder()
                .id(UUID.fromString("25d31a88-17ed-44cf-aee3-7701e0c4e5d4"))
                .email("Dinh.Nghia@hrs.com")
                .firstName("Dinh")
                .lastName("Nghia")
                .build();
    }
}
