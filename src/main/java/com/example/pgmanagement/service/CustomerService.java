package com.example.pgmanagement.service;

import com.example.pgmanagement.model.CustomerEntity;

import com.example.pgmanagement.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<CustomerEntity> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void saveCustomer(CustomerEntity customer) {

        customerRepository.save(customer);
    }

    public CustomerEntity getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public void deleteCustomer(Long id) {

        customerRepository.deleteById(id);
    }

    public List<CustomerEntity> getPendingCustomers(){
        return customerRepository.getPendingCustomer();
    }
}
