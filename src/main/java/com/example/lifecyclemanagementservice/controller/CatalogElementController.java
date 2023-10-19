package com.example.lifecyclemanagementservice.controller;

import com.example.lifecyclemanagementservice.model.CatalogElement;
import com.example.lifecyclemanagementservice.model.LifecycleStatus;
import com.example.lifecyclemanagementservice.service.CatalogElementServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/catalog-element")
public class CatalogElementController {

    @Autowired
    private CatalogElementServiceImpl catalogElementServiceImpl;

    @PatchMapping("/{id}/status")
    public ResponseEntity updateStatus(@PathVariable Long id, @RequestBody LifecycleStatus newStatus) {

        // retrieve catalog element
        CatalogElement catalogElement = catalogElementServiceImpl.getCatalogElementById(id);

        // validate and update the status
        boolean validity = catalogElementServiceImpl.validateAndUpdateStatus(catalogElement, newStatus);

        // HTTP response
        return (validity) ? new ResponseEntity(HttpStatus.OK): new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<CatalogElement> getAllCatalogElements() {

        return catalogElementServiceImpl.getAllCatalogElements();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CatalogElement getCatalogElementById(@PathVariable Long id) {

        return catalogElementServiceImpl.getCatalogElementById(id);
    }
}
