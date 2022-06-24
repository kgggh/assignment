package com.triple.assignment.model.api;

import lombok.Getter;

@Getter
public class ResponseBase {
    private String message = "Success";

    public ResponseBase(String message) {
        this.message = message;
    }
}
