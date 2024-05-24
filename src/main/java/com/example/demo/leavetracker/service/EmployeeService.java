package com.example.demo.leavetracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.leavetracker.dto.AttendanceDto;
import com.example.demo.leavetracker.dto.EmployeeDto;
import com.example.demo.leavetracker.entity.AttendanceEntity;
import com.example.demo.leavetracker.entity.EmployeeEntity;
import com.example.demo.leavetracker.exceptions.ResourceNotFoundException;
import com.example.demo.leavetracker.mapper.EmployeeMapper;
import com.example.demo.leavetracker.repository.EmployeeRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmployeeService {

	@Autowired
	EmployeeRepo employeeRepo;

	@Autowired
	private EmployeeMapper employeeMapper;

	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		log.info("Creating employee: {}", employeeDto);
		EmployeeEntity employee = employeeMapper.toEntity(employeeDto);
		log.info("Converted employee: {}", employee);

		EmployeeEntity savedEmployee = employeeRepo.save(employee);
		return employeeMapper.toDto(savedEmployee);

	}
	
	public EmployeeDto getEmployeeById(Long employeeId) {
		EmployeeEntity employee=employeeRepo.findById(employeeId).
				orElseThrow(()->new ResourceNotFoundException("EmployeeEntity","id",employeeId));
		return employeeMapper.toDto(employee);
	}
	
	public List<EmployeeDto> getAllEmployees() {
		List<EmployeeEntity> allEmployees = employeeRepo.findAll();
		return allEmployees.stream().map(employee -> employeeMapper.toDto(employee)).toList();
	}


	public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
		EmployeeEntity employee = employeeRepo.findById(employeeDto.getEmployeeId())
				.orElseThrow(() -> new ResourceNotFoundException("EmployeeEntity", "Id", employeeDto.getEmployeeId()));
		
		if (employeeDto.getEmployeeName() != null) {
			employee.setEmployeeName(employeeDto.getEmployeeName());
		}
		
		if (employeeDto.getRole() != null) {
			employee.setRole(employee.getRole());
		}
		
		EmployeeEntity updatedEmployee = employeeRepo.save(employee);
		log.info("Updated employee: {}", updatedEmployee);
		return employeeMapper.toDto(updatedEmployee);
	}
	

}
