package com.example.demo.leavetracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.leavetracker.dto.AttendanceDto;
import com.example.demo.leavetracker.dto.EmployeeDto;
import com.example.demo.leavetracker.entity.AttendanceEntity;
import com.example.demo.leavetracker.entity.AttendanceStatus;
import com.example.demo.leavetracker.entity.EmployeeEntity;
import com.example.demo.leavetracker.exceptions.AttendanceAlreadyExistsException;
import com.example.demo.leavetracker.exceptions.ResourceNotFoundException;
import com.example.demo.leavetracker.mapper.AttendanceMapper;
import com.example.demo.leavetracker.mapper.EmployeeMapper;
import com.example.demo.leavetracker.repository.AttendanceRepo;
import com.example.demo.leavetracker.repository.EmployeeRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AttendanceService {

	@Autowired
	private AttendanceRepo attendanceRepo;

	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private EmployeeMapper employeeMapper;

	@Autowired
	private AttendanceMapper attendanceMapper;

	
	/** Marking or Creating a new attendance**/

	public AttendanceDto MarkAttendance(AttendanceDto attendanceDto, Long employeeId) {
		log.info("marking  a new Attendance...");

		// Find the employee by ID or throw an exception if not found
		EmployeeEntity employeeEntity = employeeRepo.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("EmployeeEntity", "employeeId", employeeId));

		log.info("Employee found: {}", employeeId);

		// Check if attendance already exists for the employee and date
		Optional<AttendanceEntity> existingAttendanceOptional = attendanceRepo.findByEmployeeAndDate(employeeEntity,
				attendanceDto.getDate());

		if (existingAttendanceOptional.isPresent()) {
			// Attendance already exists for the employee on the given date, throw an error
			throw new AttendanceAlreadyExistsException(
					"Attendance already created for employee with ID " + employeeId + " on " + attendanceDto.getDate());
		}

		// Attendance record doesn't exist, proceed with creation
		AttendanceEntity attendance = attendanceMapper.toEntity(attendanceDto);
		attendance.setEmployee(employeeEntity);

		// Save attendance record
		AttendanceEntity newAttendance = attendanceRepo.save(attendance);
		AttendanceDto newAttendanceDto = attendanceMapper.toDto(newAttendance);
		newAttendanceDto.setEmployeeId(employeeId);
		return newAttendanceDto;
	}
	
	/**updating the attendanceStatus**/
	public boolean updateAttendance(AttendanceDto attendanceDto, Long employeeId) {
	    // Find the employee by ID or throw an exception if not found
	    EmployeeEntity employeeEntity = employeeRepo.findById(employeeId)
	            .orElseThrow(() -> new ResourceNotFoundException("EmployeeEntity", "employeeId", employeeId));

	    // Find existing attendance record for the employee and date, if any
	    Optional<AttendanceEntity> attendanceOptional = attendanceRepo.findByEmployeeAndDate(employeeEntity, attendanceDto.getDate());
	    
	    if (attendanceOptional.isPresent()) {
	        // Attendance record exists, update its status
	        AttendanceEntity attendance = attendanceOptional.get();
	        attendance.setAttendanceStatus(AttendanceStatus.valueOf(attendanceDto.getAttendanceStatus()));
	        attendanceRepo.save(attendance);
	        return true; // Attendance status updated
	    } else {
	        // Attendance record does not exist, no action needed
	        return false; 
	    }
	}


	public void deleteAttendance(Long attendanceId) {
		AttendanceEntity attendance = attendanceRepo.findById(attendanceId)
				.orElseThrow(() -> new ResourceNotFoundException("AttendanceMapper", "attendanceid", attendanceId));
		attendanceRepo.delete(attendance);
	}

	public List<AttendanceDto> getAllAttendances() {
		List<AttendanceEntity> allAttendances = attendanceRepo.findAll();
		return allAttendances.stream().map(attendance -> attendanceMapper.toDto(attendance)).toList();
	}

	public AttendanceDto getAttendanceById(Long attendanceId) {
		AttendanceEntity attendance = attendanceRepo.findById(attendanceId)
				.orElseThrow(() -> new ResourceNotFoundException("AttendanceMapper", "attendanceId", attendanceId));
		return attendanceMapper.toDto(attendance);
	}

	public List<AttendanceDto> getAttendanceByEmployee(Long employeeId) {
		EmployeeEntity employee = employeeRepo.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("employee", "employeeId", employeeId));
		System.out.println(employee);
		List<AttendanceEntity> attendances = attendanceRepo.findByEmployee(employee);
		System.out.println(attendances);
		return attendances.stream().map(attendance -> attendanceMapper.toDto(attendance)).toList();
	}

	

}
