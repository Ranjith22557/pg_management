package com.example.pgmanagement.controller;

import com.example.pgmanagement.model.CustomerEntity;
import com.example.pgmanagement.model.PaymentEntity;
import com.example.pgmanagement.service.CustomerService;
import com.example.pgmanagement.service.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PaymentController {



    private final CustomerService customerService;

    public PaymentController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping("/payments")
    public String showPayment(Model model){

        model.addAttribute("payments",customerService.getPendingCustomers());
        return "payment";
    }

    @GetMapping("/getCustomer")
    public String getCustomer(@RequestParam(value = "customerId", required = false) Long customerId,Model model){

        List<CustomerEntity> customer = new ArrayList<>();

        if(customerId != null){
          CustomerEntity  customerObj = customerService.getCustomerById(customerId);

          if(customerObj != null){
              customer.add(customerObj);
          }
        }else{
            customer = customerService.getPendingCustomers();
        }

        model.addAttribute("payments",customer);
        return "payment";
    }
}
