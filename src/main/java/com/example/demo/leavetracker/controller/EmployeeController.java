package com.example.demo.leavetracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.leavetracker.dto.ApiResponse;
import com.example.demo.leavetracker.dto.EmployeeDto;
import com.example.demo.leavetracker.dto.LoginRequestDto;
import com.example.demo.leavetracker.exceptions.InvalidCredentialsException;
import com.example.demo.leavetracker.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "*")
public class EmployeeController {	

    @Autowired	
    private EmployeeService employeeService;

    @PostMapping("auth/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequest) {
        try {
            if (loginRequest.getPassword() == null || loginRequest.getPassword().isEmpty()) {
                return ResponseEntity.badRequest().body("Password cannot be empty");
            }
            var employeeDto = employeeService.login(loginRequest);
            return ResponseEntity.ok(employeeDto);
        } catch (InvalidCredentialsException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                                 .body("Invalid username or password");
        }
    }
     

   /** @PostMapping("auth/refresh")
    public ResponseEntity<EmployeeDto> refreshToken(@RequestBody EmployeeDto employeeDto) {
        try {
            return ResponseEntity.ok(employeeService.refreshToken(employeeDto));
        } catch (Exception e) {
            log.error("Error occurred while refreshing token", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }**/

    @PostMapping("auth/createEmployee")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        log.info("Received request to create employee: {}", employeeDto);
        try {
            EmployeeDto createEmployeeDto = employeeService.createEmployee(employeeDto);
            log.info("Employee created successfully: {}", createEmployeeDto);
            return new ResponseEntity<>(createEmployeeDto, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error occurred while creating employee", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("admin/getAllemployees")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        try {
            List<EmployeeDto> allEmployees = employeeService.getAllEmployees();
            return new ResponseEntity<>(allEmployees, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred while retrieving all employees", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("admin/getEmployeeById/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long employeeId) {
        try {
            EmployeeDto employee = employeeService.getEmployeeById(employeeId);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred while retrieving employee by ID: {}", employeeId, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("admin/updateEmployee/{employeeId}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long employeeId, @RequestBody EmployeeDto employee) {
        try {
            return ResponseEntity.ok(employeeService.updateEmployee(employeeId, employee));
        } catch (Exception e) {
            log.error("Error occurred while updating employee with ID: {}", employeeId, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("auth/myInfo")
    public ResponseEntity<EmployeeDto> getMyInfo() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();
            EmployeeDto employeeDto = employeeService.getMyInfo(email);
            return ResponseEntity.ok(employeeDto);
        } catch (Exception e) {
            log.error("Error occurred while retrieving personal info", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("admin/deleteEmployee/{employeeId}")
    public ApiResponse deleteEmployee(@PathVariable Long employeeId) {
        try {
            employeeService.deleteEmployee(employeeId);
            return new ApiResponse("Employee successfully deleted", true);
        } catch (Exception e) {
            log.error("Error occurred while deleting employee with ID: {}", employeeId, e);
            return new ApiResponse("Error occurred while deleting employee", false);
        }
    }
}
