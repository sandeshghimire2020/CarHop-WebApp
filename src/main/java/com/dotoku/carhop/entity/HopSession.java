package com.dotoku.carhop.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.catalina.User;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@ToString
@Entity
@Table(name = "hop_session")
public class HopSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String originAddress;
    private String originCity;
    private String originState;
    private String originZip;

    private String destinationAddress;
    private String destinationCity;
    private String destinationState;
    private String destinationZip;

    private LocalDateTime time;

    @Column(nullable = false)
    private int availableSeats; // Add validation to avoid negative values

    private LocalDateTime expiresAt;

    private Boolean returnBack;

    private LocalDateTime returnTime;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private HopUser hopUser; // Reference to the user who created the session

    @OneToMany(mappedBy = "hopSession", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HopRequest> rideRequests; // Relation to ride requests

}

