package com.example.pgmanagement.repository;

import com.example.pgmanagement.model.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Long> {

    @Query(value = "select room_number from room where capacity > occupied_spots ",nativeQuery = true)
    List<String> getVacantRoomsList();

    RoomEntity findByRoomNumber(String roomNumber);
}
