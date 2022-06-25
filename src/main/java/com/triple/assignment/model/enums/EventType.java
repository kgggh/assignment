package com.triple.assignment.model.enums;

import lombok.Getter;

@Getter
public enum EventType {
    REVIEW("리뷰이벤트"),
    TEST("XX이벤트");

    private String description;

    EventType(String description) {
        this.description = description;
    }
}
