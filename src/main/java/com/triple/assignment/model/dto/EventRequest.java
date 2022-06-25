package com.triple.assignment.model.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.triple.assignment.model.enums.EventActionType;
import com.triple.assignment.model.enums.EventType;
import lombok.ToString;

@JsonTypeInfo(
        use= JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ReviewEventRequestDto.class, name = "REVIEW"),
        @JsonSubTypes.Type(value = MockEventRequestDto.class, name = "TEST")
})
@ToString
public abstract class EventRequest {
    public EventType type;
    public EventActionType action;

    public abstract EventType getType();
    public abstract EventActionType getAction();
    public abstract void setAction(EventActionType action);
}

