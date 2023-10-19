package com.example.lifecyclemanagementservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CatalogElement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LifecycleStatus status;

    @ManyToMany(mappedBy = "catalogElements")
    private Set<Customer> customers;
}
