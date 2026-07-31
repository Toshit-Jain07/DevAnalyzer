package com.toshit.DevAnalyzer.service;

import com.toshit.DevAnalyzer.dto.ErrorExceptionResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class ExceptionsHandle {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorExceptionResp> handleRuntimeException(RuntimeException ex) {

        log.error("RuntimeException caught: {}", ex.getMessage(), ex);

        ErrorExceptionResp error = new ErrorExceptionResp(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorExceptionResp> handleGenericException(Exception ex) {
        log.error("Unexpected exception caught: {}", ex.getMessage(), ex);

        ErrorExceptionResp error = new ErrorExceptionResp(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                "Something went wrong. Please try again later."
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
