package com.triple.assignment.model.dto;

import com.triple.assignment.model.enums.EventActionType;
import com.triple.assignment.model.enums.EventType;
import lombok.Builder;
import lombok.Data;
import java.util.UUID;

@Data
public class PointResponseDto {
    private UUID userId;
    private EventType type;
    private EventActionType actionType;
    private String content;
    private int point;
    private int appliedPoint;

    @Builder
    public PointResponseDto(UUID userId, EventType type, EventActionType actionType, String content, int point, int appliedPoint) {
        this.userId = userId;
        this.type = type;
        this.actionType = actionType;
        this.content = content;
        this.point = point;
        this.appliedPoint = appliedPoint;
    }
}
