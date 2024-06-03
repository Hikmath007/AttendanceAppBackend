package com.example.demo.leavetracker.exceptions;

public class AttendanceAlreadyExistsException extends RuntimeException {


    public AttendanceAlreadyExistsException(String message) {
        super(message);
    }
}