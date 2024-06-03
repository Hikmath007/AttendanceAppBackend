package com.example.demo.leavetracker.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.leavetracker.dto.EmployeeDto;
import com.example.demo.leavetracker.dto.LoginRequestDto;
import com.example.demo.leavetracker.entity.EmployeeEntity;
import com.example.demo.leavetracker.exceptions.EmployeeNotFoundException;
import com.example.demo.leavetracker.exceptions.InvalidCredentialsException;
import com.example.demo.leavetracker.exceptions.ResourceNotFoundException;
import com.example.demo.leavetracker.mapper.EmployeeMapper;
import com.example.demo.leavetracker.repository.EmployeeRepo;
import com.example.demo.leavetracker.security.JWTUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmployeeService {

	@Autowired
	private EmployeeDto employeeDto;

	@Autowired
	EmployeeRepo employeeRepo;

	@Autowired
	private EmployeeMapper employeeMapper;

	@Autowired
	private JWTUtils jwtUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	
	
	public EmployeeDto login(LoginRequestDto loginRequest) {
		
		System.out.println("line 43 " + loginRequest.getEmail());

		// Retrieve the employee details by email
		EmployeeEntity employee = employeeRepo.findByEmail(loginRequest.getEmail())
				.orElseThrow(() -> new InvalidCredentialsException("Invalid Email", "Employee not found"));

		
		if (!passwordEncoder.matches(loginRequest.getPassword(), employee.getPassword())) {
			// If employee not found or password is incorrect, return
			throw new InvalidCredentialsException("Invalid Password", "Password does not match");
		}
		// Generate tokens
		var jwt = jwtUtils.generateToken(employee);
		var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), employee);
		
		System.out.println(refreshToken);
		
		employeeDto.setEmail(employee.getEmail());
		employeeDto.setEmployeeName(employee.getEmployeeName());	
		employeeDto.setEmployeeId(employee.getEmployeeId());
		employeeDto.setToken(jwt);
		employeeDto.setStatusCode(HttpStatus.OK);
		employeeDto.setRole(employee.getRole());
		employeeDto.setRefreshToken(refreshToken);
		
		System.out.print(	"line 63 " + 	employeeDto);
		return employeeDto;
	}

	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		
		EmployeeEntity employee = employeeMapper.toEntity(employeeDto);
		employee.setPassword(passwordEncoder.encode(employee.getPassword()));
		EmployeeEntity savedEmployee = employeeRepo.save(employee);
		  
		return employeeDto;

	}

	// use this method if required

	/**
	 * public EmployeeDto refreshToken(EmployeeDto refreshTokenRequest) {
	 * 
	 * String employeeEmail =
	 * jwtUtils.extractUsername(refreshTokenRequest.getToken()); EmployeeEntity
	 * employees = employeeRepo.findByEmail(employeeEmail).orElseThrow(); if
	 * (jwtUtils.isTokenValid(refreshTokenRequest.getToken(), employees)) { var jwt
	 * = jwtUtils.generateToken(employees); employeeDto.setToken(jwt);
	 * employeeDto.setRefreshToken(refreshTokenRequest.getToken());
	 * employeeDto.setMessage("Successfully Refreshed Token"); }
	 * 
	 * return employeeDto;
	 * 
	 * }
	 **/

	public EmployeeDto getEmployeeById(Long employeeId) {
		EmployeeEntity employee = employeeRepo.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("EmployeeEntity", "id", employeeId));
		System.out.println(employee);
		return employeeMapper.toDto(employee);
	}

	public List<EmployeeDto> getAllEmployees() {
		List<EmployeeEntity> allEmployees = employeeRepo.findAll();
		return allEmployees.stream().map(employee -> employeeMapper.toDto(employee)).toList();
	}

	public EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeDto) {
		
		EmployeeEntity employee = employeeRepo.findById(employeeId)
				.orElseThrow(() ->new ResourceNotFoundException("emoloyeeid not found", "with", employeeId));

		if (employeeDto.getEmployeeName() != null) {
			employee.setEmployeeName(employeeDto.getEmployeeName());
		}

		if (employeeDto.getRole() != null) {
			employee.setRole(employeeDto.getRole());
		}
		
		if (employeeDto.getEmail() != null) {
	        employee.setEmail(employeeDto.getEmail());
	    }

		EmployeeEntity updatedEmployee = employeeRepo.save(employee);
		log.info("Updated employee: {}", updatedEmployee);
		return employeeDto;
	}

	public EmployeeDto getMyInfo(String email) {
		Optional<EmployeeEntity> employeeEmail = employeeRepo.findByEmail(email);
		if (employeeEmail.isPresent()) {
			EmployeeEntity employee = employeeEmail.get();
			return employeeMapper.toDto(employee);
		} else {
			throw new EmployeeNotFoundException("EmployeeEntity", "email", email);
		}
	}

	public void deleteEmployee(Long employeeId) {
		EmployeeEntity employee = employeeRepo.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("EmployeeEntity", "id", employeeId));
		employeeRepo.delete(employee);
	}
}
