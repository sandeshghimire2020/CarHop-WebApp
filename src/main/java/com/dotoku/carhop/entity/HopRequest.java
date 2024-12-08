package com.dotoku.carhop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@Entity
@Table(name = "hop_request")
public class HopRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "hop_user_id", nullable = false)
    @JsonIgnore
    private HopUser hopUser; // The user making the request

    @ManyToOne()
    @JoinColumn(name = "hop_session_id", nullable = false)
    @JsonIgnore
    private HopSession hopSession; // The session the request is for

    private String status; // pending, approved, rejected, etc.

    private LocalDateTime requestTime;
}
