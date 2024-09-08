package com.dotoku.carhop.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReviewDto {

    private Long id;

    @NotBlank(message = "User who reviewed is required.")
    private Long reviewedBy;

    private String comment;

    @NotBlank(message = "Rating is required.")
    private Integer rating;

}
