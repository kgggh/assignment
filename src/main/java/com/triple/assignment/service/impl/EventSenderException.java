package com.triple.assignment.service.impl;

import com.triple.assignment.model.api.ErrorCode;

public class EventSenderException extends RuntimeException {
    private ErrorCode errorCode;

    public EventSenderException() {
        super();
    }

    public EventSenderException(String message) {
        super(message);
    }

    public EventSenderException(ErrorCode errorCode) {
        super(errorCode.getReason());
        this.errorCode = errorCode;
    }
    public EventSenderException(Throwable cause) {
        super(cause);
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
