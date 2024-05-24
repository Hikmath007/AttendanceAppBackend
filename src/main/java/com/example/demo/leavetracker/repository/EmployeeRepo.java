package com.example.demo.leavetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.leavetracker.entity.EmployeeEntity;

@Repository
public interface EmployeeRepo  extends JpaRepository <EmployeeEntity, Long>{

}
