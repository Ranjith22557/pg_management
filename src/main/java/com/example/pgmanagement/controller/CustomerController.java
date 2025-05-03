package com.example.pgmanagement.controller;

import com.example.pgmanagement.model.CustomerEntity;
import com.example.pgmanagement.model.RoomEntity;
import com.example.pgmanagement.service.CustomerService;
import com.example.pgmanagement.service.EmailService;
import com.example.pgmanagement.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    private final RoomService roomService;

    @Autowired
    private EmailService emailService;

    public CustomerController (RoomService roomService){
        this.roomService = roomService;
    }

    @GetMapping
    public String viewCustomers(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        return "customer";
    }

    @GetMapping("/add")
    public String addCustomerForm(Model model) {
        CustomerEntity customer = new CustomerEntity();
        RoomEntity rooms = new RoomEntity();

        //setTotalAmount
        customer.setTotalAmount(new BigDecimal(5000));
        model.addAttribute("customer", customer);

        //Available Rooms
        List<String> room = roomService.getVacantRooms();
        model.addAttribute("rooms", room);

        return "customerForm";
    }

    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute com.example.pgmanagement.model.CustomerEntity customer, Model model, BindingResult result) {

        if (result.hasErrors()){
            model.addAttribute("error","Please correct the error in the from");
            return "redirect:/customers";
        }

        String roomNumber = customer.getRoomNumber();
        RoomEntity room = roomService.getRoomNumber(roomNumber);
        if(room != null){
            room.setOccupiedSpots(room.getOccupiedSpots()+1);
            roomService.saveRoom(room);

            customer.setRoom(room);
        }else {
            return ("Error Room not found");
        }

        try{
            emailService.sendCustomerEmail(customer.getEmail(), customer.getName(),
                    customer.getId(), customer.getRoomNumber(),
                    customer.getJoinDate(),customer.getTotalAmount(),
                    customer.getPaidAmount(),customer.getPendingAmount());
        }catch (Exception e) {
            throw new RuntimeException("Exception while sending Mail",e);
        }
        model.addAttribute("message","Customer saved successfully! Email send.");

        customerService.saveCustomer(customer);
        return "redirect:/customers";
    }

    @GetMapping("/edit/{id}")
    public String editCustomer(@PathVariable Long id, Model model) {
        model.addAttribute("customer", customerService.getCustomerById(id));
        return "customerForm";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id, RedirectAttributes redirectAttributes) {

        CustomerEntity customer = customerService.getCustomerById(id);

        RoomEntity room = roomService.getRoomNumber(customer.getRoomNumber());
        if(room != null){
            room.setOccupiedSpots(room.getOccupiedSpots()+1);
            roomService.saveRoom(room);

            customer.setRoom(null);
            customerService.deleteCustomer(id);
            redirectAttributes.addFlashAttribute("successMessage", "Customer deleted successfully.");
        }else {
            redirectAttributes.addFlashAttribute("errorMessage", "Room not found for the customer.");
        }

        return "redirect:/customers";
    }
}
