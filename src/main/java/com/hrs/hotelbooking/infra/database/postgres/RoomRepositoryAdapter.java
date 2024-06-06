package com.hrs.hotelbooking.infra.database.postgres;

import com.hrs.hotelbooking.application.repository.RoomRepository;
import com.hrs.hotelbooking.domain.model.Room;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class RoomRepositoryAdapter implements RoomRepository {

    private final SpringRoomRepository roomRepository;
    @Override
    public Set<Room> findRoomsInIds(Set<UUID> ids) {
        return roomRepository.findAllByIdIn(ids);
    }
}
