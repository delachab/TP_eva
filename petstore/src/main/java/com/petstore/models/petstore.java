package com.petstore.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "petstore")
public class petstore<animal> {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String managerName;

    @OneToMany(mappedBy = "petStore")
    private List<Animal> animals;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    public Object getId() {
        return null;
    }

    // Getters and setters
}
