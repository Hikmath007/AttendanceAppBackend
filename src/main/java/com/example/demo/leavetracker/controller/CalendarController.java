package com.example.demo.leavetracker.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.leavetracker.dto.CalendarDateDto;
import com.example.demo.leavetracker.service.CalendarService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CalendarController {
    
    @Autowired
    private CalendarService calendarService;

    @GetMapping("/calendar") // Removed the redundant "/api" prefix
    public List<CalendarDateDto> getCalendarDates(@RequestParam String startDate, @RequestParam String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        return calendarService.getCalendarDates( start, end);
    }
}
