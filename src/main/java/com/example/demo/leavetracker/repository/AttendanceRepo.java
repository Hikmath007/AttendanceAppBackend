package com.example.demo.leavetracker.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.leavetracker.entity.AttendanceEntity;
import com.example.demo.leavetracker.entity.EmployeeEntity;

@Repository
public interface AttendanceRepo extends JpaRepository<AttendanceEntity, Long> {

	List<AttendanceEntity> findByEmployee(EmployeeEntity employeeEntity);

	Optional<AttendanceEntity> findByDate(LocalDate date);

	Optional<AttendanceEntity> findByEmployeeAndDate(EmployeeEntity employeeEntity, LocalDate date);

//	  @Query(value="SELECT * FROM attendance_table WHERE date = :date AND emp_id = :eId",nativeQuery = true)
//		List<AttendanceEntity> checkForDuplicacy(Date date,Long eId);
}
