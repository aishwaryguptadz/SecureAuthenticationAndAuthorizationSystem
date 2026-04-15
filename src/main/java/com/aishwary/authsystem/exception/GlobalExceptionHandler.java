package com.aishwary.authsystem.exception;

import com.aishwary.authsystem.dto.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ApiResponse<?> handleRuntime(RuntimeException e) {
        return new ApiResponse<>("error", e.getMessage(), null);
    }
}
