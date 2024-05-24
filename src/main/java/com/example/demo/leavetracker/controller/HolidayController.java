package com.example.demo.leavetracker.controller;

import com.example.demo.leavetracker.dto.HolidayDto;
import com.example.demo.leavetracker.service.HolidayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/holidays")
@CrossOrigin(origins = "*")
public class HolidayController {

    @Autowired
    private HolidayService holidayService;

    @PostMapping
    public ResponseEntity<HolidayDto> createHoliday(@RequestBody HolidayDto holidayDto) {
        log.info("Received request to create holiday: {}", holidayDto);
        HolidayDto createdHoliday = holidayService.createHoliday(holidayDto);
        log.info("Created holiday: {}", createdHoliday);
        return new ResponseEntity<>(createdHoliday, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<HolidayDto>> getAllHolidays() {
        log.info("Received request to fetch all holidays");
        List<HolidayDto> holidays = holidayService.getAllHolidays();
        log.info("Fetched all holidays: {}", holidays);
        return new ResponseEntity<>(holidays, HttpStatus.OK);
    }

    @GetMapping("/{holidayId}")
    public ResponseEntity<HolidayDto> getHolidayById(@PathVariable Long holidayId) {
        log.info("Received request to fetch holiday by id: {}", holidayId);
        HolidayDto holidayDto = holidayService.getHolidayById(holidayId);
        log.info("Fetched holiday by id {}: {}", holidayId, holidayDto);
        return new ResponseEntity<>(holidayDto, HttpStatus.OK);
    }

 

}
