package com.example.demo.leavetracker.dto;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.example.demo.leavetracker.entity.EmployeeEntity;

import lombok.Data;

@Component
@Data
public class EmployeeDto {

	private HttpStatus statusCode;
	
	private Long employeeId;

    private String employeeName;
    
     private String password;
    
    private String ExpirationDate;
    
    private String email;
    
    private String role;
    
	private String token;
	
	private String refreshToken;
	
	private String message;
	
	private EmployeeEntity employee;

	
}
