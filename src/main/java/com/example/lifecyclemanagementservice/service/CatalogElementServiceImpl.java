package com.example.lifecyclemanagementservice.service;

import com.example.lifecyclemanagementservice.model.CatalogElement;
import com.example.lifecyclemanagementservice.model.LifecycleStatus;

import com.example.lifecyclemanagementservice.repository.CatalogElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CatalogElementServiceImpl implements CatalogElementService {

    @Autowired
    private CatalogElementRepository catalogElementRepository;

    @Override
    public CatalogElement getCatalogElementById(Long id) {
        Optional<CatalogElement> optionalCatalogElement = catalogElementRepository.findById(id);

        // return found data or identifiable null values
        return optionalCatalogElement.orElseGet(() -> CatalogElement.builder()
                .id(null)
                .status(null)
                .build());
    }

    @Override
    public boolean validateAndUpdateStatus(CatalogElement catalogElement, LifecycleStatus newStatus) {

        // validate the transition
        boolean validity = validateTransition(catalogElement.getStatus(), newStatus);

        if (validity) {
            // update the status
            catalogElement.setStatus(newStatus);

            // remove only obsolete catalog element
            if (catalogElement.getStatus() == LifecycleStatus.OBSOLETE) {
                catalogElementRepository.delete(catalogElement);
            }
            // save changes to non-obsolete catalog element
            else {
                catalogElementRepository.save(catalogElement);
            }
        }

        return validity;
    }

    private boolean validateTransition(LifecycleStatus currentStatus, LifecycleStatus newStatus) {

        // reject validity for null value
        if (currentStatus == null) return false;

        switch (currentStatus) {
            case IN_STUDY -> {
                return newStatus == LifecycleStatus.IN_DESIGN;
            }
            case IN_DESIGN -> {
                return newStatus == LifecycleStatus.IN_TEST;
            }
            case IN_TEST -> {
                return newStatus == LifecycleStatus.REJECTED || newStatus == LifecycleStatus.ACTIVE;
            }
            case ACTIVE -> {
                return newStatus == LifecycleStatus.RETIRED || newStatus == LifecycleStatus.LAUNCHED;
            }
            case RETIRED -> {
                return newStatus == LifecycleStatus.OBSOLETE;
            }
            case LAUNCHED -> {
                return newStatus == LifecycleStatus.RETIRED;
            }
            case REJECTED, OBSOLETE -> {}
        }
        return false;
    }

    @Override
    public Iterable<CatalogElement> getAllCatalogElements() {
        return catalogElementRepository.findAll();
    }
}
