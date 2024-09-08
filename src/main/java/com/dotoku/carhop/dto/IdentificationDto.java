package com.dotoku.carhop.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class IdentificationDto {

    private Long id;

    @NotBlank(message = "Identification number is required.")
    @Size(min = 1,max = 25, message = "Identification number should be at most 25 characters.")
    private String number;

    private String type;

    @FutureOrPresent(message = "Expiration date must be in Future.")
    @NotBlank(message = "Expiration date is required.")
    private LocalDate expiration;

    private String country;
}
