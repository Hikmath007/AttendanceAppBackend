package com.example.demo.leavetracker.exceptions;

import java.util.Date;

public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;

    public ErrorDetails(String message, String details) {
        this.timestamp = new Date();
        this.message = message;
        this.details = details;
    }

    // Getters for timestamp, message, and details
}
