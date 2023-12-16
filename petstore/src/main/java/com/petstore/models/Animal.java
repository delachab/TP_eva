package com.petstore.models;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // One table per class strategy
public abstract class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date birth;

    @Column
    private String color;

    @ManyToOne
    @JoinColumn(name = "petstore_id")
    private PetStore petStore;

    // Getters and setters
}

@Entity
@Table(name = "fish")
public class fish extends Animal {

    @Column
    @Enumerated(EnumType.STRING)
    private FishLivEnv livingEnv; // Assuming FishLivEnv is an enum

    // Getters and setters
}

@Entity
@Table(name = "cat")
public class Cat extends Animal {

    @Column
    private String chipId;

    // Getters and setters
}
