package com.example.lifecyclemanagementservice.controller;

import com.example.lifecyclemanagementservice.model.CatalogElement;
import com.example.lifecyclemanagementservice.model.Customer;
import com.example.lifecyclemanagementservice.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @PatchMapping("/{id}/status")
    public ResponseEntity purchaseCatalogElement(@PathVariable Long customerId, @RequestBody Long catalogId) {

        // retrieve customer
        Customer customer = customerServiceImpl.getCustomerById(customerId);

        // validate and update the status
        boolean validity = customerServiceImpl.validateAndPerformPurchase(customer, catalogId);

        // HTTP response
        return (validity) ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Customer> getAllCatalogElements() {

        return customerServiceImpl.getAllCustomers();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Customer getCatalogElementById(@PathVariable Long id) {

        return customerServiceImpl.getCustomerById(id);
    }
}
