package com.example.lifecyclemanagementservice.bootstrap;

import com.example.lifecyclemanagementservice.model.CatalogElement;
import com.example.lifecyclemanagementservice.model.Customer;
import com.example.lifecyclemanagementservice.model.LifecycleStatus;
import com.example.lifecyclemanagementservice.repository.CatalogElementRepository;
import com.example.lifecyclemanagementservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/*
*  Creating defined counts of catalog elements and customers that will be stored
*  in the H2 database, to be ready for Postman testing when the application is started
*  or for instant unit (JUnit) testing.
* */

@Component
public class BootStrapData implements CommandLineRunner {

    @Autowired
    private CatalogElementRepository catalogElementRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void run(String... args) {

        // create catalog elements
        int catalogElementsCount = 5;

        for (int i = 1; i <= catalogElementsCount; i++) {
            CatalogElement catalogElement = CatalogElement.builder()
                    .id((long) i)
                    .status(LifecycleStatus.IN_STUDY)
                    .build();

            // persist each catalog element
            catalogElementRepository.save(catalogElement);
        }

        // create customers
        int customersCount = 5;

        for (int i = 1; i <= customersCount; i++) {
            Customer customer = Customer.builder()
                    .id((long) i)
                    .build();

            // persist each customer
            customerRepository.save(customer);
        }
    }
}
