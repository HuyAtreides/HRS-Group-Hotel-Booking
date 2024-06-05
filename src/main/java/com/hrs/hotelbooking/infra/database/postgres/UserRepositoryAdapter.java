package com.hrs.hotelbooking.infra.database.postgres;

import com.hrs.hotelbooking.application.repository.UserRepository;
import com.hrs.hotelbooking.domain.model.User;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class UserRepositoryAdapter implements UserRepository {
    private final SpringUserRepository userRepository;

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
