package com.example.demo.leavetracker.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


@Data
public class AttendanceDto {
	private Long attendanceId;

	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    
    private String attendanceStatus;
    
    private String attendanceType;
    
    private LocalDateTime loginTime;
    
    private LocalDateTime logoutTime;
    
    private String ipv4Address;
    
    private Long employeeId;

}
