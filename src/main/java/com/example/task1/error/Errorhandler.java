package com.example.task1.error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@ControllerAdvice
public class Errorhandler {

    private ErrorResponse errorResponse;

    @Autowired
    public Errorhandler(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public Errorhandler() {
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception exc) {
        OffsetDateTime timestamp = OffsetDateTime.now(ZoneOffset.UTC);
        errorResponse.setTimestamp(timestamp);
        errorResponse.setMessage(exc.getMessage());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }
}
