package com.example.employeeDutyService.mapper;

import com.example.employeeDutyService.domain.EmployeeDuty;
import com.example.employeeDutyService.dto.CreateEmployeeDutyDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CreateEmployeeDutyMapper {
    EmployeeDuty toEntity(CreateEmployeeDutyDto createEmployeeDutyDto);

}