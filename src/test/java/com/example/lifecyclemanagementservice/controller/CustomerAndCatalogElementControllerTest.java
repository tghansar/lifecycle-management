package com.example.lifecyclemanagementservice.controller;

import com.example.lifecyclemanagementservice.model.LifecycleStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CustomerAndCatalogElementControllerTest {

    @Autowired
    private CustomerController customerController;

    @Autowired
    private CatalogElementController catalogElementController;

    @Test
    void testLaunched() {
        // test setup
        assertEquals(new ResponseEntity(HttpStatus.OK),
                catalogElementController.updateStatus(5L, LifecycleStatus.IN_DESIGN));
        assertEquals(new ResponseEntity(HttpStatus.OK),
                catalogElementController.updateStatus(5L, LifecycleStatus.IN_TEST));
        assertEquals(new ResponseEntity(HttpStatus.OK),
                catalogElementController.updateStatus(5L, LifecycleStatus.ACTIVE));
        assertEquals(new ResponseEntity(HttpStatus.OK),
                catalogElementController.updateStatus(5L, LifecycleStatus.LAUNCHED));

        // test purchase
        assertEquals(new ResponseEntity(HttpStatus.OK),
                customerController.purchaseCatalogElement(1L, 5L));
    }

    @Test
    void testRetiredPurchaseNonExistingCustomer() {
        // test setup
        assertEquals(new ResponseEntity(HttpStatus.OK),
                catalogElementController.updateStatus(6L, LifecycleStatus.IN_DESIGN));
        assertEquals(new ResponseEntity(HttpStatus.OK),
                catalogElementController.updateStatus(6L, LifecycleStatus.IN_TEST));
        assertEquals(new ResponseEntity(HttpStatus.OK),
                catalogElementController.updateStatus(6L, LifecycleStatus.ACTIVE));
        assertEquals(new ResponseEntity(HttpStatus.OK),
                catalogElementController.updateStatus(6L, LifecycleStatus.LAUNCHED));
        assertEquals(new ResponseEntity(HttpStatus.OK),
                catalogElementController.updateStatus(6L, LifecycleStatus.RETIRED));

        // test purchase
        assertEquals(new ResponseEntity(HttpStatus.NOT_ACCEPTABLE),
                customerController.purchaseCatalogElement(1L, 6L));
    }

    @Test
    void testRetiredPurchaseExistingCustomer() {
        // test setup
        assertEquals(new ResponseEntity(HttpStatus.OK),
                catalogElementController.updateStatus(7L, LifecycleStatus.IN_DESIGN));
        assertEquals(new ResponseEntity(HttpStatus.OK),
                catalogElementController.updateStatus(7L, LifecycleStatus.IN_TEST));
        assertEquals(new ResponseEntity(HttpStatus.OK),
                catalogElementController.updateStatus(7L, LifecycleStatus.ACTIVE));
        assertEquals(new ResponseEntity(HttpStatus.OK),
                catalogElementController.updateStatus(7L, LifecycleStatus.LAUNCHED));
        assertEquals(new ResponseEntity(HttpStatus.OK),
                catalogElementController.updateStatus(7L, LifecycleStatus.RETIRED));

        // test purchase
        assertEquals(new ResponseEntity(HttpStatus.OK),
                customerController.purchaseCatalogElement(1L, 7L));
    }

    @Test
    void testObsolete() {
        // test setup
        assertEquals(new ResponseEntity(HttpStatus.OK),
                catalogElementController.updateStatus(8L, LifecycleStatus.IN_DESIGN));
        assertEquals(new ResponseEntity(HttpStatus.OK),
                catalogElementController.updateStatus(8L, LifecycleStatus.IN_TEST));
        assertEquals(new ResponseEntity(HttpStatus.OK),
                catalogElementController.updateStatus(8L, LifecycleStatus.ACTIVE));
        assertEquals(new ResponseEntity(HttpStatus.OK),
                catalogElementController.updateStatus(8L, LifecycleStatus.LAUNCHED));
        assertEquals(new ResponseEntity(HttpStatus.OK),
                catalogElementController.updateStatus(8L, LifecycleStatus.RETIRED));
        assertEquals(new ResponseEntity(HttpStatus.OK),
                catalogElementController.updateStatus(8L, LifecycleStatus.OBSOLETE));

        // test purchase
        assertEquals(new ResponseEntity(HttpStatus.OK),
                customerController.purchaseCatalogElement(1L, 8L));
    }
}