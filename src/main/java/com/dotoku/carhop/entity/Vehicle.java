package com.dotoku.carhop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false) // Added unique constraint for plate number
    private String plateNumber;

    private String type;  // SUV, sedan, truck, etc.

    private String make; // Toyota, BMW, Honda

    private String model;

    private String year; // Manufacturing year
}

