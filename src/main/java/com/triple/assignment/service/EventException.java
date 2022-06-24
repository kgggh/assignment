package com.triple.assignment.service;

import com.triple.assignment.model.api.ErrorCode;

public class EventException extends RuntimeException {
    public EventException() {
        super();
    }

    public EventException(String message) {
        super(message);
    }

    public EventException(ErrorCode errorCode) {
        super(errorCode.getReason());
    }

    public EventException(Throwable cause) {
        super(cause);
    }
}
