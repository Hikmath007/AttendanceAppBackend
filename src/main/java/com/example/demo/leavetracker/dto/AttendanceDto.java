package com.example.demo.leavetracker.dto;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;


@Data
public class AttendanceDto {
	private Long attendanceId;

    private Date date;
    
    private String attendanceStatus;
    
    private String attendanceType;
    
    private LocalDateTime loginTime;
    
    private LocalDateTime logoutTime;
    
    private String ipv4Address;
    
    private Long employeeId;

}
