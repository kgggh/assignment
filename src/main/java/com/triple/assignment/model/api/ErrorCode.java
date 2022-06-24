package com.triple.assignment.model.api;

import lombok.Getter;

@Getter
public enum ErrorCode {
    NOT_FOUND("Not Found",-404000),
    BAD_REQUEST("Bad Request",-400000),
    INTERNAL_SERVER_ERROR("Internal Server Error",-500000),

    EXIST_REVIEW("There is a review written.", -400100),
    NOT_EXIST_REVIEW("There is a review written.", -400101),

    NOT_EXIST_POINT("Points do not exist", -400200),

    NOT_EXIST_USER("Member does not exist.", -400300);

    private final String reason;
    private final int code;

    ErrorCode(String reason, int code) {
        this.reason = reason;
        this.code = code;
    }
}
