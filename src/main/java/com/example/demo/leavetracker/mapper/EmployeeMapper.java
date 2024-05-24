package com.example.demo.leavetracker.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.example.demo.leavetracker.dto.EmployeeDto;
import com.example.demo.leavetracker.entity.EmployeeEntity;

@Mapper(componentModel = "spring")
public interface EmployeeMapper extends EntityMapper <EmployeeDto,EmployeeEntity> {

}
