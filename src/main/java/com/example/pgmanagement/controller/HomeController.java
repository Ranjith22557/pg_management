package com.example.pgmanagement.controller;

import com.example.pgmanagement.service.HomeService;
import com.example.pgmanagement.service.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RequestMapping("/home")
public class HomeController {

    private final HomeService homeService;

    public HomeController (HomeService homeService) {
        this.homeService = homeService;

    }

    @GetMapping()
    public String showHome(Model model){

        model.addAttribute("totalCustomers",homeService.getTotalCustomer());
        model.addAttribute("pendingPayments",homeService.getTotalPendingAmount());


        return "home";
    }
}
