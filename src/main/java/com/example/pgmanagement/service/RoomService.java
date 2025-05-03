package com.example.pgmanagement.service;

import com.example.pgmanagement.DTO.RoomDTO;
import com.example.pgmanagement.model.RoomEntity;
import com.example.pgmanagement.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public List<RoomEntity> getAllRooms() {
        return roomRepository.findAll();
    }

    public RoomEntity getRoomById(Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    public RoomEntity addRoom(RoomEntity room) {
        return roomRepository.save(room);
    }

    public RoomEntity updateRoom(Long id, RoomEntity updatedRoom) {
        RoomEntity room = roomRepository.findById(id).orElse(null);
        if (room != null) {
            room.setRoomNumber(updatedRoom.getRoomNumber());
            room.setCapacity(updatedRoom.getCapacity());
            room.setOccupied(updatedRoom.isOccupied());
            return roomRepository.save(room);
        }
        return null;
    }

    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }

    public RoomEntity markAsOccupied(Long id) {
        RoomEntity room = roomRepository.findById(id).orElse(null);
        if (room != null) {
            room.setOccupied(true);
            return roomRepository.save(room);
        }
        return null;
    }

    public RoomEntity markAsVacant(Long id) {
        RoomEntity room = roomRepository.findById(id).orElse(null);
        if (room != null) {
            room.setOccupied(false);
            return roomRepository.save(room);
        }
        return null;
    }

    public List<String> getVacantRooms(){
        return roomRepository.getVacantRoomsList();
    }

    public RoomDTO getRoomDetails(String roomNumber){
        RoomEntity room = roomRepository.findByRoomNumber(roomNumber);

        if(room != null){
            return new RoomDTO(
                    room.getRoomNumber(),
                    room.getCapacity(),
                    room.getOccupiedSpots(),
                    room.getCapacity()-room.getOccupiedSpots());
        }
        return null;
    }

    public void saveRoom(RoomEntity room){
        roomRepository.save(room);
    }

    public RoomEntity getRoomNumber(String roomNumber){
       return roomRepository.findByRoomNumber(roomNumber);

    }
}
