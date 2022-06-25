package com.triple.assignment.global;

import com.triple.assignment.model.api.ApiResponse;
import com.triple.assignment.model.api.ErrorCode;
import com.triple.assignment.service.impl.EventListenerException;
import com.triple.assignment.service.impl.PointException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> exceptionHandler(final Exception e) {
        log.error("Exception: {}", e.getMessage(), e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse(ErrorCode.INTERNAL_SERVER_ERROR, e.getMessage()));
    }

    @ExceptionHandler({EventListenerException.class})
    public ResponseEntity<Object> exceptionHandler(EventListenerException e) {
        log.error("EventListenerException: {}", e.getMessage(), e);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse(e.getErrorCode()));
    }

    @ExceptionHandler({PointException.class})
    public ResponseEntity<Object> exceptionHandler(final PointException e) {
        log.error("PointException: {}", e.getMessage(), e);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse(e.getErrorCode()));
    }
}
