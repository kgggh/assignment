package com.triple.assignment.model.api;

import lombok.Getter;

@Getter
public enum ErrorCode {
    NOT_FOUND("Not Found",-404_000),
    BAD_REQUEST("Bad Request",-400_000),
    INTERNAL_SERVER_ERROR("Internal Server Error",-500_000),

    EXIST_REVIEW("There is a review written", -400_100),
    NOT_EXIST_REVIEW("There is a review written", -400_101),

    NOT_EXIST_POINT("Points do not exist", -400_200),

    NOT_EXIST_USER("Member does not exist", -400_300),

    NOT_EXIST_PlACE("Place does not exist", -400_400);

    private String reason;
    private int code;

    ErrorCode(String reason, int code) {
        this.reason = reason;
        this.code = code;
    }
}
