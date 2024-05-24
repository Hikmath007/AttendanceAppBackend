package com.example.demo.leavetracker.exceptions;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class ResourceNotFoundException extends RuntimeException{
     final String resourceName;
    final String fieldName;
     final Long fieldValue;

    public ResourceNotFoundException(String resourceName,String fieldName,Long employeeId){
        super(String.format(" %s not found with %s: %s", resourceName,fieldName,employeeId));
        this.resourceName=resourceName;
        this.fieldName=fieldName;
        this.fieldValue=employeeId;
    }

}