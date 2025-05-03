package com.example.pgmanagement.controller;

import com.example.pgmanagement.DTO.RoomDTO;
import com.example.pgmanagement.model.RoomEntity;
import com.example.pgmanagement.repository.RoomRepository;
import com.example.pgmanagement.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping
    public String listRooms(Model model) {
        model.addAttribute("rooms", roomService.getAllRooms());
        return "room"; // Thymeleaf template name (rooms.html)
    }

    @PostMapping("/add")
    public String addRoom(@ModelAttribute RoomEntity room, RedirectAttributes redirectAttributes ) {

        RoomEntity existingRoom = roomService.getRoomNumber(room.getRoomNumber());
        if(existingRoom !=null){
            redirectAttributes.addFlashAttribute("message","Room Number already exists.");
            return "redirect:/rooms";
        }
        roomService.addRoom(room);
        return "redirect:/rooms";
    }

    @PostMapping("/update")
    public String updateRoom(@RequestParam Long id, @RequestParam String roomNumber, @RequestParam int capacity, @RequestParam int occupiedSpots) {
        RoomEntity room = roomService.getRoomById(id);
        if (room != null) {
            room.setRoomNumber(roomNumber);
            room.setCapacity(capacity);
            room.setOccupiedSpots(occupiedSpots);
            roomService.updateRoom(id, room);
        }
        return "redirect:/rooms";
    }

    @GetMapping("/delete/{id}")
    public String deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return "redirect:/rooms";
    }

    @GetMapping("/occupy/{id}")
    public String markAsOccupied(@PathVariable Long id) {
        roomService.markAsOccupied(id);
        return "redirect:/rooms";
    }

    @GetMapping("/vacate/{id}")
    public String markAsVacant(@PathVariable Long id) {
        roomService.markAsVacant(id);
        return "redirect:/rooms";
    }

    @GetMapping("/{roomNumber}")
    public ResponseEntity<RoomDTO> getRoomDetail(@PathVariable String roomNumber){
        RoomDTO roomDTO = roomService.getRoomDetails(roomNumber);
        return roomDTO != null ? ResponseEntity.ok(roomDTO) : ResponseEntity.notFound().build();
    }

}
