package com.example.lifecyclemanagementservice.controller;

import com.example.lifecyclemanagementservice.model.LifecycleStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CatalogElementControllerTest {

    @Autowired
    private CatalogElementController catalogElementController;

    @Test
    void testRejectedFlow() {
        assertEquals(new ResponseEntity(HttpStatus.NOT_ACCEPTABLE),
                catalogElementController.updateStatus(1L, LifecycleStatus.ACTIVE));
        assertEquals(new ResponseEntity(HttpStatus.OK),
                catalogElementController.updateStatus(1L, LifecycleStatus.IN_DESIGN));
        assertEquals(new ResponseEntity(HttpStatus.OK),
                catalogElementController.updateStatus(1L, LifecycleStatus.IN_TEST));
        assertEquals(new ResponseEntity(HttpStatus.OK),
                catalogElementController.updateStatus(1L, LifecycleStatus.REJECTED));
        assertEquals(new ResponseEntity(HttpStatus.NOT_ACCEPTABLE),
                catalogElementController.updateStatus(1L, LifecycleStatus.ACTIVE));
    }

    @Test
    void testRetiredObsoleteFlow() {
        assertEquals(new ResponseEntity(HttpStatus.OK),
                catalogElementController.updateStatus(2L, LifecycleStatus.IN_DESIGN));
        assertEquals(new ResponseEntity(HttpStatus.OK),
                catalogElementController.updateStatus(2L, LifecycleStatus.IN_TEST));
        assertEquals(new ResponseEntity(HttpStatus.OK),
                catalogElementController.updateStatus(2L, LifecycleStatus.ACTIVE));
        assertEquals(new ResponseEntity(HttpStatus.OK),
                catalogElementController.updateStatus(2L, LifecycleStatus.RETIRED));
        assertEquals(new ResponseEntity(HttpStatus.OK),
                catalogElementController.updateStatus(2L, LifecycleStatus.OBSOLETE));
    }

    @Test
    void testLaunchedFlow() {
        assertEquals(new ResponseEntity(HttpStatus.OK),
                catalogElementController.updateStatus(3L, LifecycleStatus.IN_DESIGN));
        assertEquals(new ResponseEntity(HttpStatus.OK),
                catalogElementController.updateStatus(3L, LifecycleStatus.IN_TEST));
        assertEquals(new ResponseEntity(HttpStatus.OK),
                catalogElementController.updateStatus(3L, LifecycleStatus.ACTIVE));
        assertEquals(new ResponseEntity(HttpStatus.OK),
                catalogElementController.updateStatus(3L, LifecycleStatus.LAUNCHED));
    }

    @Test
    void testLaunchedToRetiredObsoleteFlow() {
        assertEquals(new ResponseEntity(HttpStatus.OK),
                catalogElementController.updateStatus(4L, LifecycleStatus.IN_DESIGN));
        assertEquals(new ResponseEntity(HttpStatus.OK),
                catalogElementController.updateStatus(4L, LifecycleStatus.IN_TEST));
        assertEquals(new ResponseEntity(HttpStatus.OK),
                catalogElementController.updateStatus(4L, LifecycleStatus.ACTIVE));
        assertEquals(new ResponseEntity(HttpStatus.OK),
                catalogElementController.updateStatus(4L, LifecycleStatus.LAUNCHED));
        assertEquals(new ResponseEntity(HttpStatus.OK),
                catalogElementController.updateStatus(4L, LifecycleStatus.RETIRED));
        assertEquals(new ResponseEntity(HttpStatus.OK),
                catalogElementController.updateStatus(4L, LifecycleStatus.OBSOLETE));
    }

    @Test
    void testRejectedFlow2() {
        assertEquals(new ResponseEntity(HttpStatus.NOT_ACCEPTABLE),
                catalogElementController.updateStatus(5L, LifecycleStatus.ACTIVE));
        assertEquals(new ResponseEntity(HttpStatus.OK),
                catalogElementController.updateStatus(5L, LifecycleStatus.IN_DESIGN));
        assertEquals(new ResponseEntity(HttpStatus.OK),
                catalogElementController.updateStatus(5L, LifecycleStatus.IN_TEST));
        assertEquals(new ResponseEntity(HttpStatus.OK),
                catalogElementController.updateStatus(5L, LifecycleStatus.REJECTED));
        assertEquals(new ResponseEntity(HttpStatus.NOT_ACCEPTABLE),
                catalogElementController.updateStatus(5L, LifecycleStatus.ACTIVE));
    }
}