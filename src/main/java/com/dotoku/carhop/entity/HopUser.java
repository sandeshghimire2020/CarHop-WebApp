package com.dotoku.carhop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
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

    @Column(nullable = false, unique = true)
    private String email;

    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private String gender;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Identification identification;

    private boolean verified;

    //maybe vehicle can be one to many? and make a default one?
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Vehicle vehicle;

    @OneToMany(mappedBy = "hopUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;

//    @OneToMany(mappedBy = "hopUser", cascade = CascadeType.ALL, orphanRemoval = true)
//    @ToString.Exclude
//    private List<HopRequest> rideRequests;

    @Column(nullable = false)
    private String password;

//    @Column(nullable = false)
    private String role; // e.g., "USER", "ADMIN", "DRIVER"


}
