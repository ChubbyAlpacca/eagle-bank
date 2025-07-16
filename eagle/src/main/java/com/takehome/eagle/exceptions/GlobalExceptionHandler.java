package com.takehome.eagle.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EagleBankException.class)
    public ResponseEntity<Object> handleEagleBankException(EagleBankException ex) {
        if (ex.getErrorResponse() != null) {
            return ResponseEntity
                    .status(ex.getHttpstatusCode())
                    .body(ex.getErrorResponse());
        }

        return ResponseEntity
                .status(ex.getHttpstatusCode())
                .body(new ErrorMessage(ex.getMessage()));
    }

    // Optional: fallback message format
    public record ErrorMessage(String message) {}
}
