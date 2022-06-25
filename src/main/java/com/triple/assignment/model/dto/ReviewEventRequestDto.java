package com.triple.assignment.model.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.triple.assignment.model.enums.EventActionType;
import com.triple.assignment.model.enums.EventType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@JsonTypeName("REVIEW")
@Getter
@ToString(callSuper = true)
public class ReviewEventRequestDto extends EventRequest {
    @NotNull(message = "reviewId is required")
    private UUID reviewId;

    @NotNull(message = "userId is required")
    private UUID userId;

    @NotNull(message = "placeId is required")
    private UUID placeId;

    private String content;
    private List<UUID> attachedPhotoIds = new ArrayList<>();

    @Override
    public EventType getType() {
        return EventType.REVIEW;
    }

    @Override
    public EventActionType getAction() {
        return this.action;
    }

    @Override
    public void setAction(EventActionType action) {
        this.action = action;
    }

    @Builder
    public ReviewEventRequestDto(UUID reviewId, UUID userId, UUID placeId, String content, List<UUID> attachedPhotoIds, EventActionType eventActionType) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.placeId = placeId;
        this.content = content;
        this.attachedPhotoIds = attachedPhotoIds;
        super.action = eventActionType;
    }
}