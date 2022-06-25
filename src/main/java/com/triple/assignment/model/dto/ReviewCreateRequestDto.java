package com.triple.assignment.model.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ReviewCreateRequestDto {
    private UUID placeId;
    private UUID userId;
    private List<PhotoInfo> photos;
    private String content;

    @Data
    public static class PhotoInfo {
        private String photoName;
    }
}
