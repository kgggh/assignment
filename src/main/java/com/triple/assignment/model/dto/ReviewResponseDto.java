package com.triple.assignment.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ReviewResponseDto {
    private UUID reviewId;
    private UUID placeId;
    private UUID userId;
    private List<PhotoInfo> photos;

    @Builder
    public ReviewResponseDto(UUID reviewId, UUID placeId, UUID userId, List<PhotoInfo> photos) {
        this.reviewId = reviewId;
        this.placeId = placeId;
        this.userId = userId;
        this.photos = photos;
    }

    @Data
    private class  PhotoInfo {
        private String photoName;
    }
}
