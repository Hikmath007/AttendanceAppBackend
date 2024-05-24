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

	public AttendanceDto createAttendance(AttendanceDto attendanceDto, Long employeeId) {
		log.info("Creating a new Attendance...");
		EmployeeEntity employeeEntity = employeeRepo.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("EmployeeEntity", " employeeId ", employeeId));
		EmployeeDto employeeDto = employeeMapper.toDto(employeeEntity);

		log.info("Employee found: {}", employeeId);

		AttendanceEntity attendance = attendanceMapper.toEntity(attendanceDto);
		attendance.setEmployee(employeeEntity);
		// we will return this below
		log.info("Converted Course: {}", attendance);

		AttendanceEntity newAttendance = attendanceRepo.save(attendance);
		AttendanceDto newAttendanceDto = attendanceMapper.toDto(newAttendance);
		newAttendanceDto.setEmployeeId(employeeId);
		return newAttendanceDto;
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

	public boolean markAttendanceStatus(AttendanceDto attendanceDto) {
		try {
			Optional<AttendanceEntity> attendanceOptional = attendanceRepo.findByDate(attendanceDto.getDate());
			AttendanceEntity attendance = attendanceOptional.isPresent() ? attendanceOptional.get()
					: new AttendanceEntity();
			log.info("Attendance Entity: {}", attendance);

			attendance.setDate(attendanceDto.getDate());
			attendance.setAttendanceStatus(AttendanceStatus.valueOf(attendanceDto.getAttendanceStatus()));
			attendanceRepo.save(attendance);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
