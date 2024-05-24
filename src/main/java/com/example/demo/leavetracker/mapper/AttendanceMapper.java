package com.example.demo.leavetracker.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.demo.leavetracker.dto.AttendanceDto;
import com.example.demo.leavetracker.entity.AttendanceEntity;


@Mapper(componentModel = "spring")
public interface AttendanceMapper  extends  EntityMapper <AttendanceDto, AttendanceEntity> {
	
	@Mapping(source ="employee.employeeId",target="employeeId")
	public AttendanceDto toDto(AttendanceEntity attendanceEntity);
	
	@Mapping(target = "employee", ignore = true)  // Assuming EmployeeEntity is set elsewhere
    AttendanceEntity toEntity(AttendanceDto attendanceDto);

}
