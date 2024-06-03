package com.example.demo.leavetracker.exceptions;

import lombok.Data;

@Data
public class EmployeeNotFoundException extends RuntimeException{
    final String resourceName;
   final String fieldName;
    final String fieldValue;

   public EmployeeNotFoundException(String resourceName,String fieldName,String fieldValue){
       super(String.format(" %s not found with %s: %s", resourceName,fieldName,fieldValue));
       this.resourceName=resourceName;
       this.fieldName=fieldName;
       this.fieldValue=fieldValue;
   }}
