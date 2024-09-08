package com.dotoku.carhop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class VehicleDto {

    @NotBlank(message = "PlateNumber is required.")
    @Size(min = 6, message = "Plate number should at least 6.")
    private String plateNumber;

    @NotBlank(message = "Type is required.")
    private String type;  //suv, sedan, truck

    @NotBlank(message = "Make is required.")
    private String make; //toyata, BMW, Honda

    @NotBlank(message = "Model is required.")
    private String model;

    @NotBlank(message = "Year is required.")
    private String year; //make year

}
