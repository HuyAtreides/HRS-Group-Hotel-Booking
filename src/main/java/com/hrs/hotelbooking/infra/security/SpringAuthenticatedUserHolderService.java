package com.hrs.hotelbooking.infra.security;

import com.hrs.hotelbooking.application.AuthenticatedUserHolderService;
import com.hrs.hotelbooking.domain.model.User;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class SpringAuthenticatedUserHolderService implements AuthenticatedUserHolderService {

    @Override
    public User getAuthenticatedUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Object principal = securityContext.getAuthentication().getPrincipal();

        if (!(principal instanceof UserDetails)) {
            return null;
        }

        return (User) principal;
    }
}
