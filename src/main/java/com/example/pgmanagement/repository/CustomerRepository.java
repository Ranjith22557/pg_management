package com.example.pgmanagement.repository;


import com.example.pgmanagement.model.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    @Query(value = "select coalesce(sum(c.pending_amount),0) from customer c",nativeQuery = true)
    Double getTotalPendingAmount();

    @Query(value = "select * from customer where pending_amount > 0 ",nativeQuery = true)
    List<CustomerEntity> getPendingCustomer();


}
