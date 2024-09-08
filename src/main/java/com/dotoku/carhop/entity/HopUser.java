package com.dotoku.carhop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@ToString
@Entity
@Table(name = "hopUser")
public class HopUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    private String middleName;

    @Column(nullable = false)
    private String lastName;

    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private String gender;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Identification identification;

    private boolean verified;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Vehicle vehicle; // Fixed typo from Vehical to Vehicle

    @OneToMany(mappedBy = "hopUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;

    @OneToMany(mappedBy = "hopUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HopRequest> rideRequests; // Relation to RideRequest entity
}
