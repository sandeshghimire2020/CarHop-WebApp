package com.dotoku.carhop.service;

import com.dotoku.carhop.dto.ReviewDto;
import com.dotoku.carhop.entity.HopUser;
import com.dotoku.carhop.entity.Review;
import com.dotoku.carhop.repository.ReviewRepository;
import com.dotoku.carhop.repository.UserRepository;
import com.dotoku.carhop.security.SecurityUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {

    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public ResponseEntity<ReviewDto> addReview(ReviewDto reviewDto, Long userId){

        HopUser hopUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));

        String loggedInEmail = SecurityUtil.getLoggedInEmail();
        if (hopUser.getEmail().equals(loggedInEmail)) {
            throw new IllegalStateException("You are not allowed to review yourself!");
        }

        Review review = new Review();

        review.setReviewedBy(reviewDto.getReviewedBy());
        review.setRating(reviewDto.getRating());
        review.setComment(reviewDto.getComment());
        review.setHopUser(hopUser);

        reviewRepository.save(review);

        reviewDto.setId(review.getId());
        return ResponseEntity.ok(reviewDto);
    }

    public ResponseEntity<List<ReviewDto>> getReviewsById(Long userId){

        HopUser hopUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));

        List<Review> reviews = hopUser.getReviews();

        List<ReviewDto> reviewDtos = reviews.stream()
                .map(this::mapReviewToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(reviewDtos);
    }

    public ReviewDto mapReviewToDto(Review review) {
        ReviewDto dto = new ReviewDto();
        dto.setId(review.getId());
        dto.setComment(review.getComment());
        dto.setReviewedBy(review.getReviewedBy());
        dto.setRating(review.getRating());
        return dto;
    }

}
