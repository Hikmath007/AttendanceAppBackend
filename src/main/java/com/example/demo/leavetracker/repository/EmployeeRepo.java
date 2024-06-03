package com.example.demo.leavetracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.leavetracker.entity.EmployeeEntity;

@Repository
public interface EmployeeRepo  extends JpaRepository <EmployeeEntity, Long>{

	Optional<EmployeeEntity> findByEmail(String email);
}
