package com.example.demo.leavetracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.leavetracker.dto.AttendanceDto;
import com.example.demo.leavetracker.dto.EmployeeDto;
import com.example.demo.leavetracker.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins = "*")
public class EmployeeController {	
 @Autowired	
 private EmployeeService employeeService;
	
	@PostMapping("/create")
//	@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
	public ResponseEntity<EmployeeDto>createEmployee(@RequestBody EmployeeDto employeeDto){
		log.info("Received request to create employee: {}", employeeDto);
	    try {    
		EmployeeDto createEmployeeDto= employeeService.createEmployee(employeeDto);
		 log.info("Employee created successfully: {}", createEmployeeDto);
		 return new ResponseEntity<>(createEmployeeDto, HttpStatus.CREATED);
	
	} catch(Exception e) {
        log.error("Error occurred while creating employee", e);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }}
	    
	    
	    @GetMapping("/employees")
//	    @PreAuthorize("hasAnyRole('EMPLOYEE', 'MANAGER', 'ADMIN')")
	    public ResponseEntity<List<EmployeeDto>>getAllEmployees(){

	        List<EmployeeDto> allemployees = employeeService.getAllEmployees();
	        return new ResponseEntity<>(allemployees, HttpStatus.OK);	    	

	}
	
	
	    };

	
	