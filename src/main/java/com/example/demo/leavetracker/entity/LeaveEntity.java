package com.example.demo.leavetracker.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="leaveEntity")
public class LeaveEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long leaveId;
    private Date startDate;
    private Date endDate;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "leave_status")
    private LeaveStatus leaveStatus;
    
    @ManyToOne
    @JoinColumn(name = "employeeId")
    private EmployeeEntity employee;

	
}
