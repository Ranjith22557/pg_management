package com.example.pgmanagement.service;

import com.example.pgmanagement.model.PaymentEntity;
import com.example.pgmanagement.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

        @Autowired
        private PaymentRepository paymentRepository;

        public List<PaymentEntity> getPaymentDetails(){
            return paymentRepository.findAll();
        }
}
