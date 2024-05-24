package com.example.demo.leavetracker.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "HolidayTable")
public class HolidayEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long holidayId;
	private Date  holidayDate;
	private String holidayDescription;


}
