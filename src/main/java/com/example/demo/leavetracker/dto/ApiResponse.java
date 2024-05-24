package com.example.demo.leavetracker.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@NoArgsConstructor
@Slf4j

public class ApiResponse {
    private String message;
    private boolean success;

    public ApiResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
        log.info("ApiResponse created - Message: {}, Success: {}", message, success);
    }
}



