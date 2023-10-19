package com.example.lifecyclemanagementservice.service;

import com.example.lifecyclemanagementservice.model.CatalogElement;
import com.example.lifecyclemanagementservice.model.LifecycleStatus;

import java.util.List;

public interface CatalogElementService {

    CatalogElement getCatalogElementById(Long id);

    boolean validateAndUpdateStatus(CatalogElement catalogElement, LifecycleStatus newStatus);

    Iterable<CatalogElement> getAllCatalogElements();

}
