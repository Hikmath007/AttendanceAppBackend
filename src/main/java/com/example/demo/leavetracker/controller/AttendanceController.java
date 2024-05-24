package com.example.demo.leavetracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
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
import com.example.demo.leavetracker.dto.AttendanceDto;
import com.example.demo.leavetracker.service.AttendanceService;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService; //TO CREATE ATTENDANCE WE NEED SERVICE


    @PostMapping("/employees/{employeeId}/attendance")
//    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<AttendanceDto> createAttendance(@RequestBody AttendanceDto attendanceDto, @PathVariable Long employeeId) {
    	log.info("Creating attendance for employee with ID: {}", employeeId);
    	AttendanceDto createAttendance = attendanceService.createAttendance(attendanceDto, employeeId);
    	log.info("Attendance created successfully for employee with ID: {}", employeeId);
        return new ResponseEntity<>(createAttendance, HttpStatus.CREATED);
    }

    //getAttendances by Employee
    @GetMapping("/employees/{employeeId}/attendance")
//    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<List<AttendanceDto>> getAttendanceByEmployee(@PathVariable Long employeeId) {
        List<AttendanceDto> attendances =attendanceService.getAttendanceByEmployee(employeeId);
        return new ResponseEntity<>(attendances, HttpStatus.OK);
    }

    @GetMapping("/attendances")
    public ResponseEntity<List<AttendanceDto>> getAllAttendances() {

        List<AttendanceDto> allattendances = attendanceService.getAllAttendances();
        return new ResponseEntity<>(allattendances, HttpStatus.OK);
    }

    @GetMapping("/attendance/{attendanceId}")
    public ResponseEntity<AttendanceDto> getAttendanceById(@PathVariable Long attendanceId) {
    	AttendanceDto attendanceDto = attendanceService.getAttendanceById(attendanceId);
        return new ResponseEntity<>(attendanceDto, HttpStatus.OK);
    }

      @DeleteMapping("/attendance/{attendanceId}")
    public ApiResponse deleteAttendance(@PathVariable Long attendanceId) {
        attendanceService.deleteAttendance(attendanceId);

   return new ApiResponse("attendance successfully deleted", true);
      }

    
    @PutMapping("/markAttendanceStatus")
    public ResponseEntity<String> markAttendanceStatus(@RequestBody AttendanceDto attendanceDto) {
    	
        if (attendanceService.markAttendanceStatus(attendanceDto))
        	return ResponseEntity.ok("Stored response.");
        else
        	return new ResponseEntity<>("Error while marking response.", HttpStatus.INTERNAL_SERVER_ERROR);
    }    
		
	}

