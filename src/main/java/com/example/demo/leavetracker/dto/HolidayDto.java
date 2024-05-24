package com.example.demo.leavetracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class HolidayDto {
    private Long holidayId;
    private Date holidayDate;
    private String holidayDescription;
}
