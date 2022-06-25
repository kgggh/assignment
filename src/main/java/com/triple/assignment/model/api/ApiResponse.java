package com.triple.assignment.model.api;

public class ApiResponse<T> extends ResponseBase {
    private T data;

    public ApiResponse() {
    }

    public ApiResponse(T data) {
        this.data = data;
    }

    public ApiResponse(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public ApiResponse(ErrorCode errorCode) {
        super(errorCode);
    }
}
