package com.jawahar.controllerservice.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(JobNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleJobNotFoundException(JobNotFoundException jobNotFoundException, HttpServletRequest httpServletRequest) {
        ErrorResponse errorResponse= ErrorResponse.builder()
                .timestamp(Instant.now())
                .error("Job Not Found")
                .path(httpServletRequest.getRequestURI())
                .message(jobNotFoundException.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(WorkerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleWorkerNotFoundException(WorkerNotFoundException workerNotFoundException, HttpServletRequest httpServletRequest) {
        ErrorResponse error = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(workerNotFoundException.getMessage())
                .path(httpServletRequest.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
