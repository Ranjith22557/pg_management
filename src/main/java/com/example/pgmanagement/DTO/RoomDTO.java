package com.example.pgmanagement.DTO;

public class RoomDTO {

    private String roomNumber;
    private int capacity;
    private int occupied;
    private int vacant;

    public RoomDTO (String roomNumber,int capacity,int occupied,int vacant){
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.occupied = occupied;
        this.vacant = vacant;
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

    public int getOccupied() {
        return occupied;
    }

    public void setOccupied(int occupied) {
        this.occupied = occupied;
    }

    public int getVacant() {
        return vacant;
    }

    public void setVacant(int vacant) {
        this.vacant = vacant;
    }

}
