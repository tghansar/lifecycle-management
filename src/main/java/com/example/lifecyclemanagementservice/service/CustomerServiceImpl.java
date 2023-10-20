package com.example.lifecyclemanagementservice.service;

import com.example.lifecyclemanagementservice.model.CatalogElement;
import com.example.lifecyclemanagementservice.model.Customer;
import com.example.lifecyclemanagementservice.model.LifecycleStatus;
import com.example.lifecyclemanagementservice.repository.CatalogElementRepository;
import com.example.lifecyclemanagementservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CatalogElementRepository catalogElementRepository;

    @Override
    public boolean validateAndPerformPurchase(Customer customer, Long catalogElementId) {

        // validate the purchase
        boolean validity = validatePurchase(customer, catalogElementId);

        if (validity) {
            Optional<CatalogElement> optionalCatalogElement =
                    catalogElementRepository.findById(catalogElementId);

            CatalogElement retrievedCatalogElement = optionalCatalogElement.get();

            customer.getCatalogElements().add(retrievedCatalogElement);
        }

        return validity;
    }

    private boolean validatePurchase(Customer customer, Long catalogElementId) {

        // check if catalog element exists
        Optional<CatalogElement> optionalCatalogElement =
                catalogElementRepository.findById(catalogElementId);

        if (optionalCatalogElement.isEmpty()) {
            return false;
        }

        CatalogElement retrievedCatalogElement = optionalCatalogElement.get();

        // allow existing customer to purchase retired catalog element
        if (customer.getCatalogElements().contains(retrievedCatalogElement)
                && retrievedCatalogElement.getStatus() == LifecycleStatus.RETIRED) {
            return true;
        }

        // allow purchase of launched catalog element
        return retrievedCatalogElement.getStatus() == LifecycleStatus.LAUNCHED;
    }

    @Override
    public Iterable<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        // return found data or identifiable null values
        return optionalCustomer.orElseGet(() -> Customer.builder()
                .id(null)
                .build());
    }
}
