package com.triple.assignment.model.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ResponseBase {
    private String message;
    private int code;

    public ResponseBase() {
        this.message = "Success";
        this.code = 200_000;
    }
    public ResponseBase(ErrorCode errorCode) {
        this.message = errorCode.getReason();
        this.code = errorCode.getCode();
    }

    public ResponseBase(ErrorCode errorCode, String message) {
        this.message = message;
        this.code = errorCode.getCode();
    }
}
