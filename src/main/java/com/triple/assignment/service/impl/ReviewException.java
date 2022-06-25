package com.triple.assignment.service.impl;

import com.triple.assignment.model.api.ErrorCode;

public class ReviewException extends RuntimeException {
    public ReviewException() {
        super();
    }

    public ReviewException(String message) {
        super(message);
    }

    public ReviewException(ErrorCode errorCode) {
        super(errorCode.getReason());
    }

    public ReviewException(Throwable cause) {
        super(cause);
    }
}
