package com.triple.assignment.model.dto;

import com.triple.assignment.model.enums.EventActionType;
import com.triple.assignment.model.enums.EventType;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
public class PointCreateRequestDto {
    private UUID userId;
    private EventType type;
    private EventActionType actionType;
    private String content;
    private int appliedPoint;
    private int point;

    public PointCreateRequestDto(UUID userId, EventType type, EventActionType actionType, String content, int appliedPoint, int point) {
        this.userId = userId;
        this.type = type;
        this.actionType = actionType;
        this.content = content;
        this.appliedPoint = appliedPoint;
        this.point = point;
    }
}
