package com.example.demo.leavetracker.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "EmployeeTable")
public class EmployeeEntity{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long employeeId;

    private String employeeName;
    
    private String password;
    
    private String email;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private EmpRole role;

    
    @ManyToOne
    @JoinColumn(name = "department_id")
    private DepartmentEntity department;
    
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private EmployeeEntity manager;
    
//    @OneToMany(mappedBy = "manager")
//    private Set<Employee> subordinates;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<AttendanceEntity> attendance;

    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL)
    private List<LeaveEntity> leaves ;
    
  
    
    
}