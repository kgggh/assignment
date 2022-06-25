package com.triple.assignment.model.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ReviewUpdateRequestDto {
    private UUID placeId;
    private UUID userId;
    private List<PhotoInfo> photos;

    @Data
    private class  PhotoInfo {
        private String photoName;
    }
}
