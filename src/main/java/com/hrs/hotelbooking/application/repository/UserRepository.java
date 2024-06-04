package com.hrs.hotelbooking.application.repository;

import com.hrs.hotelbooking.domain.model.User;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findByEmail(String email);
}
