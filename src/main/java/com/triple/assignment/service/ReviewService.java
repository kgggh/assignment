package com.triple.assignment.service;

import com.triple.assignment.model.dto.ReviewCreateRequestDto;
import com.triple.assignment.model.dto.ReviewResponseDto;
import com.triple.assignment.model.dto.ReviewUpdateRequestDto;

import java.util.List;
import java.util.UUID;

public interface ReviewService {
    UUID create(ReviewCreateRequestDto reviewCreateRequestDto);
    void update(UUID reviewId, ReviewUpdateRequestDto reviewUpdateRequestDto);
    void delete(UUID reviewId);
    List<ReviewResponseDto> get();
}
