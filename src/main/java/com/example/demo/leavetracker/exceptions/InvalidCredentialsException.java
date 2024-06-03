package com.example.demo.leavetracker.exceptions;

public class InvalidCredentialsException extends RuntimeException {

    private final String details;

    public InvalidCredentialsException(String message, String details) {
        super(message);
        this.details = details;
    }

    public String getDetails() {
        return details;
    }
}
