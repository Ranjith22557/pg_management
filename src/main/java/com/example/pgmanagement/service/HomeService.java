package com.example.pgmanagement.service;

import com.example.pgmanagement.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class HomeService {

    private final CustomerRepository customerRepository;

    public HomeService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public long getTotalCustomer(){
        return customerRepository.count();
    }

    public Double getTotalPendingAmount(){
        return customerRepository.getTotalPendingAmount();
    }
}
