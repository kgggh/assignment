package com.triple.assignment.service.impl;

import com.triple.assignment.model.api.ErrorCode;

public class EventListenerException extends RuntimeException {
    private ErrorCode errorCode;

    public EventListenerException() {
        super();
    }

    public EventListenerException(String message) {
        super(message);
    }

    public EventListenerException(ErrorCode errorCode) {
        super(errorCode.getReason());
        this.errorCode = errorCode;
    }

    public EventListenerException(Throwable cause) {
        super(cause);
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
