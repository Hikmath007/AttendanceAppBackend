package com.example.demo.leavetracker.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "departments")
public class DepartmentEntity {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long departmentId;

	    private String departmentName;
	    
	    // One-to-many relationship with Employee
	    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
	    private List<EmployeeEntity> employees;


}
