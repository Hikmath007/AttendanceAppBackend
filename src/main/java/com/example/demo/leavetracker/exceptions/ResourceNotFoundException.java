package com.example.demo.leavetracker.exceptions;

import lombok.Data;

@Data
public class ResourceNotFoundException extends RuntimeException{
     final String resourceName;
    final String fieldName;
     final Long fieldValue;

    public ResourceNotFoundException(String resourceName,String fieldName,Long fieldValue){
        super(String.format(" %s not found with %s: %s", resourceName,fieldName,fieldValue));
        this.resourceName=resourceName;
        this.fieldName=fieldName;
        this.fieldValue=fieldValue;
    }
    
    

}