package com.dotoku.carhop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Setter
@Getter
@ToString
@Entity
@Table(name = "identification")
public class Identification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false) // Added unique constraint
    private String number;

    private String type;

    private LocalDate expiration;

    private String country;
}
