package com.dotoku.carhop.controller;


import com.dotoku.carhop.dto.ReviewDto;
import com.dotoku.carhop.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User Review controller")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/review")
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "Add a review for a user", responses = {
            @ApiResponse(responseCode = "200", description = "Review Added Successfully."),
            @ApiResponse(responseCode = "400", description = "Bad request, unable to review to user.") })
    @PostMapping(path = "/{userId}")
    public ResponseEntity<ReviewDto> addReview(@Valid @RequestBody ReviewDto reviewDto, @PathVariable Long userId) {
        return reviewService.addReview(reviewDto, userId);
    }

    @Operation(summary = "Get reviews by userId", responses = {
            @ApiResponse(responseCode = "200", description = "Retrieved reviews Successfully."),
            @ApiResponse(responseCode = "400", description = "Bad request, unable get reviews of user.") })
    @GetMapping(path = "/{userId}")
    public ResponseEntity<List<ReviewDto>> getReviewsById(@PathVariable Long userId) {
        return reviewService.getReviewsById(userId);
    }

    //delete review
    //update review

}
