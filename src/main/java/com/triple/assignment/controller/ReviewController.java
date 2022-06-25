package com.triple.assignment.controller;

import com.triple.assignment.model.api.ApiResponse;
import com.triple.assignment.model.dto.ReviewCreateRequestDto;
import com.triple.assignment.model.dto.ReviewResponseDto;
import com.triple.assignment.model.dto.ReviewUpdateRequestDto;
import com.triple.assignment.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/api/v1.0/reviews")
    public ResponseEntity<ApiResponse> createReview(@RequestBody ReviewCreateRequestDto reviewCreateRequestDto) {
        log.debug("Request Create Review Object ={}", reviewCreateRequestDto);

        UUID reviewId = reviewService.create(reviewCreateRequestDto);

        return ResponseEntity.ok(new ApiResponse());
    }

    @PatchMapping("/api/v1.0/reviews/{review_id}")
    public ResponseEntity<ApiResponse> modifyReview(@PathVariable UUID reviewId,
                                            @RequestBody ReviewUpdateRequestDto reviewUpdateRequestDto) {
        log.debug("Request Update Review reviewId ={}, Object={}", reviewId, reviewUpdateRequestDto);

        reviewService.update(reviewId, reviewUpdateRequestDto);

        return ResponseEntity.ok(new ApiResponse());
    }

    @DeleteMapping("/api/v1.0/reviews/{review_id}")
    public ResponseEntity<ApiResponse> deleteReview(@PathVariable UUID reviewId) {
        log.debug("Request Delete Review reviewId={}", reviewId);

        reviewService.delete(reviewId);

        return ResponseEntity.noContent()
                .build();
    }

    @GetMapping("/api/v1.0/reviews")
    public ResponseEntity<ApiResponse> getReviews() {
        List<ReviewResponseDto> reviewResponseDto = reviewService.get();

        return ResponseEntity.ok(new ApiResponse(reviewResponseDto));
    }
}
