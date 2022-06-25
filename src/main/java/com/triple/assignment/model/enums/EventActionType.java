package com.triple.assignment.model.enums;

import lombok.Getter;

@Getter
public enum EventActionType {
    ADD("리뷰추가"),
    MOD("리뷰수정"),
    DELETE("리뷰삭제");

    private final String description;

    EventActionType(String description) {
        this.description = description;
    }
}
