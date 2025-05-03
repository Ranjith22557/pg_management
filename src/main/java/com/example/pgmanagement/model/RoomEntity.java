package com.example.pgmanagement.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "room")
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "room_number")
    private String roomNumber;
    private int capacity;
    private boolean occupied;

    @Column(name = "occupied_spots",nullable = false)
    private int occupiedSpots = 0;

    public int getOccupiedSpots() {
        return occupiedSpots;
    }

    @Transient
    public Integer getVacant(){
        int cap = Objects.nonNull(capacity) ? capacity :0;
        int occupied = Objects.nonNull(occupiedSpots) ? occupiedSpots :0 ;

        return Math.max(cap-capacity,0);
    }

    public void setOccupiedSpots(int occupiedSpots) {
        this.occupiedSpots = occupiedSpots;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}
