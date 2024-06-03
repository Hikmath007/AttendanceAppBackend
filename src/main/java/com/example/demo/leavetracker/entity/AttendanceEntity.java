package com.example.demo.leavetracker.entity;
import java.time.LocalDate;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class AttendanceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attendanceId;

    private LocalDate date;
    
    @Enumerated(EnumType.STRING)
    private AttendanceStatus attendanceStatus;
    
    @Enumerated(EnumType.STRING)
    private AttendanceType attendanceType;
    
    @ManyToOne
    @JoinColumn(name = "employeeId")
    private EmployeeEntity employee;
    
    private LocalDate loginTime;
    
    private LocalDate logoutTime;
    
    private String ipv4Address;

    public void setAttendanceStatus(AttendanceStatus attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

	@Override
	public String toString() {
		return "AttendanceEntity [attendanceId=" + attendanceId + ", date=" + date + ", attendanceStatus="
				+ attendanceStatus + ", attendanceType=" + attendanceType + ", loginTime="
				+ loginTime + ", logoutTime=" + logoutTime + ", ipv4Address=" + ipv4Address + "]";
	}
    
    
}
