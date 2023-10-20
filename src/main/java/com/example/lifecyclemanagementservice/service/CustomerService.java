package com.example.lifecyclemanagementservice.service;

import com.example.lifecyclemanagementservice.model.Customer;

public interface CustomerService {

    boolean validateAndPerformPurchase(Customer customer, Long catalogElementId);

    Iterable<Customer> getAllCustomers();

    Customer getCustomerById(Long id);
}
