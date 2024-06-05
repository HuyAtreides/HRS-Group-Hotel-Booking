package com.hrs.hotelbooking.infra.database.postgres;

import com.hrs.hotelbooking.domain.model.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;


public interface SpringUserRepository extends CrudRepository<User, UUID> {
    Optional<User> findByEmail(String email);
}
