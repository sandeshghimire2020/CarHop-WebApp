package com.dotoku.carhop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private HopUser hopUser;

    @Column(nullable = false)
    private Long reviewedBy; // Should link to another HopUser id

    private String comment;

    @Column(nullable = false)
    private Integer rating; // Should validate between 1 and 5
}
