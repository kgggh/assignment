package com.triple.assignment.service;

import com.triple.assignment.model.api.ErrorCode;

public class PointException extends RuntimeException {

    public PointException() {
    }

    public PointException(String message) {
        super(message);
    }
    public PointException(ErrorCode errorCode) {
        super(errorCode.getReason());
    }

    public PointException(Throwable cause) {
        super(cause);
    }
}
