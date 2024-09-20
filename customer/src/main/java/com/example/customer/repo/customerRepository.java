package com.example.customer.repo;

import com.example.customer.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface customerRepository {

    @Query("SELECT c FROM Customer c WHERE c.userId = :userId")
    List<Customer> FindCustomerByUserId(Long userId);


}
