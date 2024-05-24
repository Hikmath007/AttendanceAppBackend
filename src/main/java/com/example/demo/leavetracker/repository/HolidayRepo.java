package com.example.demo.leavetracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.leavetracker.entity.HolidayEntity;

public interface HolidayRepo extends JpaRepository<HolidayEntity, Long> {

//	Optional<HolidayEntity> findById(Long holidayId);
	

}
