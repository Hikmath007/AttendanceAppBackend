//
//package com.example.demo.leavetracker.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import com.example.demo.leavetracker.service.EmployeeService;
//import com.example.demo.leavetracker.dto.EmployeeDto;
//
//@RequestMapping("/auth")
//public class AuthController {
//
//    @Autowired
//    private EmployeeService employeeService;
//
//    @PostMapping("/login")
//    public ResponseEntity<EmployeeDto> login(@RequestBody EmployeeDto login) {
//        String token = employeeService.authenticateEmployee(login.getEmail(), loginRequest.getPassword());
//        return ResponseEntity.ok(new AuthResponse(token));
//    }
//}
//
