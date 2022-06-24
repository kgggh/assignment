package com.triple.assignment.model.api;

import lombok.Getter;

@Getter
public class ErrorResponse extends ResponseBase {
    private int code;

    public ErrorResponse(ErrorCode errorCode) {
        super(errorCode.getReason());
        this.code = errorCode.getCode();
    }

    public ErrorResponse(ErrorCode errorCode, String reason) {
        super(reason);
        this.code = errorCode.getCode();
    }
}
