package com.triple.assignment.model.api;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuccessResponse<T> extends ResponseBase {
    private T data;


    public SuccessResponse(String message) {
        super(message);
    }

    public SuccessResponse(T data, String message) {
        super(message);
        this.data = data;
    }
}
