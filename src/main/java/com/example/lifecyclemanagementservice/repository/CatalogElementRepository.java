package com.example.lifecyclemanagementservice.repository;

import com.example.lifecyclemanagementservice.model.CatalogElement;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogElementRepository extends CrudRepository<CatalogElement, Long> {
}