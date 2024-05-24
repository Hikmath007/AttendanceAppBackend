package com.example.demo.leavetracker.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.leavetracker.dto.EmployeeDto;
import com.example.demo.leavetracker.dto.HolidayDto;
import com.example.demo.leavetracker.entity.EmployeeEntity;
import com.example.demo.leavetracker.entity.HolidayEntity;
import com.example.demo.leavetracker.exceptions.ResourceNotFoundException;
import com.example.demo.leavetracker.mapper.HolidayMapper;
import com.example.demo.leavetracker.repository.HolidayRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HolidayService {

    @Autowired
    private HolidayRepo holidayRepo;

    @Autowired
    private HolidayMapper holidayMapper;

    public HolidayDto createHoliday(HolidayDto holidayDto) {
        log.info("Creating holiday: {}", holidayDto);
        HolidayEntity holidayEntity = holidayMapper.toEntity(holidayDto);
        log.info("Converted holiday entity: {}", holidayEntity);

        HolidayEntity savedHolidayEntity = holidayRepo.save(holidayEntity);
        return holidayMapper.toDto(savedHolidayEntity);
    }
    
   

    public List<HolidayDto> getAllHolidays() {
        log.info("Fetching all holidays");
        List<HolidayDto> holidayDtos = new ArrayList<>();
        Iterable<HolidayEntity> holidayEntities = holidayRepo.findAll();
        for (HolidayEntity entity : holidayEntities) {
            holidayDtos.add(holidayMapper.toDto(entity));
        }
        log.info("Fetched {} holidays", holidayDtos.size());

        return holidayDtos;
    }

    public HolidayDto getHolidayById(Long holidayId) {
        log.info("Fetching holiday by id: {}", holidayId);
        HolidayEntity holidayEntity = holidayRepo.findById(holidayId)
                .orElseThrow(() -> new ResourceNotFoundException("Holiday", "id", holidayId));
        log.info("Fetched holiday: {}", holidayEntity);

        return holidayMapper.toDto(holidayEntity);
    }

    

}
