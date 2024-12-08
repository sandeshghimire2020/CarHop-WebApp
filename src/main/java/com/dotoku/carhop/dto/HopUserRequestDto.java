package com.dotoku.carhop.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class HopUserRequestDto {

    private Long id;

    @NotBlank(message = "First Name is required.")
    @Size(min = 1, max = 30, message = "First name should be at most 30 characters.")
    private String firstName;

    @Size(max = 30, message = "Middle name should be at most 30 characters.")
    private String middleName;

    @NotBlank(message = "Last Name is required.")
    @Size(min = 1, max = 30, message = "Last name should be at most 30 characters.")
    private String lastName;

    private String email;

    private String password;

    @NotBlank(message = "Date of Birth is required.")
    @Past(message = "Date of Birth mast be in the past.")
    private LocalDate dateOfBirth;

    @Pattern(regexp = "^[MF]$", message = "Gender must be 'M' or 'F'.")
    private String gender;

    @Valid
    private IdentificationDto identification;

    private boolean verified;

    private VehicleDto Vehicle;

    private List<Long> reviewsId;

    private String role;

}
