package com.triple.assignment.service.impl;

import com.triple.assignment.model.api.ErrorCode;

public class PointException extends RuntimeException {
    private ErrorCode errorCode;

    public PointException() {
    }

    public PointException(String message) {
        super(message);
    }
    public PointException(ErrorCode errorCode) {
        super(errorCode.getReason());
        this.errorCode = errorCode;
    }

    public PointException(Throwable cause) {
        super(cause);
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
