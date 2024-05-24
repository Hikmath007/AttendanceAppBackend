package com.example.demo.leavetracker.mapper;

import org.mapstruct.Mapper;

import com.example.demo.leavetracker.dto.HolidayDto;
import com.example.demo.leavetracker.entity.HolidayEntity;

@Mapper(componentModel = "spring")
public interface HolidayMapper extends EntityMapper <HolidayDto,HolidayEntity>  {

}
