package com.example.lifecyclemanagementservice.repository;

import com.example.lifecyclemanagementservice.model.Customer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}