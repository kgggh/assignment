package com.triple.assignment.global;

import com.triple.assignment.model.api.ErrorCode;
import com.triple.assignment.model.api.ErrorResponse;
import com.triple.assignment.service.EventException;
import com.triple.assignment.service.PointException;
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
                .body(new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR, e.getMessage()));
    }

    @ExceptionHandler({EventException.class})
    public ResponseEntity<Object> exceptionHandler(final EventException e) {
        log.error("AuthenticationException: {}", e.getMessage(), e);
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse(ErrorCode.EXIST_REVIEW));
    }

    @ExceptionHandler({PointException.class})
    public ResponseEntity<Object> exceptionHandler(final PointException e) {
        log.error("NotExistMemberException: {}", e.getMessage(), e);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(ErrorCode.NOT_EXIST_POINT));
    }
}
