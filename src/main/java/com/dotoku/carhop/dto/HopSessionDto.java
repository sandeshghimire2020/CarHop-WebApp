package com.dotoku.carhop.dto;

import jakarta.validation.constraints.NotNull;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HopSessionDto {

    private Long id;

    //TODO: Add validations for these address zip city
    private String originAddress;
    private String originCity;
    private String originZip;

    private String destinationAddress;
    private String destinationCity;
    private String destinationZip;

    @NotNull
    private Long VehicleId;

    private int availableSeats;

    private Boolean returnBack;
    private LocalDateTime returnTime;

    @NotNull
    private Long userId;

}
