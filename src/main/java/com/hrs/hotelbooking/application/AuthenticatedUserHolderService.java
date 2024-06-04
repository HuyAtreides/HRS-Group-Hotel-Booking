package com.hrs.hotelbooking.application;

import com.hrs.hotelbooking.domain.model.User;

public interface AuthenticatedUserHolderService {
    User getAuthenticatedUser();
}
