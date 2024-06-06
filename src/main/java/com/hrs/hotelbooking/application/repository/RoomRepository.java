package com.hrs.hotelbooking.application.repository;

import com.hrs.hotelbooking.domain.model.Room;
import java.util.Set;
import java.util.UUID;

public interface RoomRepository {
    Set<Room> findRoomsInIds(Set<UUID> ids);
}
