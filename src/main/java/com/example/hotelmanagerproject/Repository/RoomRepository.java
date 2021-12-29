package com.example.hotelmanagerproject.Repository;

import com.example.hotelmanagerproject.Model.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface RoomRepository extends CrudRepository<Room, Long> {
    Room findByRoomNum(Long roomNumber);
}
