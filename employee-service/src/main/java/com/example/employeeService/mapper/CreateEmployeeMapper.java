package com.example.employeeService.mapper;

import com.example.employeeService.domain.Employee;
import com.example.employeeService.dto.CreateEmployeeDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CreateEmployeeMapper {
    Employee toEntity(CreateEmployeeDto createEmployeeDto);
}