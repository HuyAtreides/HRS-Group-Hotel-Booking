package com.hrs.hotelbooking.infra.database.postgres;

import com.hrs.hotelbooking.domain.model.Room;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface SpringRoomRepository extends CrudRepository<Room, UUID> {
    Set<Room> findAllByIdIn(Set<UUID> ids);
}
