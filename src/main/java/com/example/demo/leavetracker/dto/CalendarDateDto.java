package com.example.demo.leavetracker.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class CalendarDateDto {
    private LocalDate date;
    private String dayOfWeek;
    private String month;
    private int year;
    private int dayNo;
}