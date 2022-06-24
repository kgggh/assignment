package com.triple.assignment.model.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.triple.assignment.model.enums.EventActionType;
import com.triple.assignment.model.enums.EventType;
import lombok.Getter;
import lombok.ToString;

@JsonTypeName("TEST")
@Getter
@ToString(callSuper = true)
public class MockEventRequestDto extends EventRequest {
    private String content;

    @Override
    public EventType getType() {
        return super.type;
    }

    @Override
    public EventActionType getActionType() {
        return this.actionType;
    }

    @Override
    public void setActionType(EventActionType actionType) {
        this.actionType = actionType;
    }
}