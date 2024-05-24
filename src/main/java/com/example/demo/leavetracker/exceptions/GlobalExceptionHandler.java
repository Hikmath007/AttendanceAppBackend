package com.example.demo.leavetracker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.leavetracker.dto.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> ResourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message, false);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }
}
