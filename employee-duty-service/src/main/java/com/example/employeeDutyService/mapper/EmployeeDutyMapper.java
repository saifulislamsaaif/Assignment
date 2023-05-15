package com.example.employeeDutyService.mapper;

import com.example.employeeDutyService.domain.EmployeeDuty;
import com.example.employeeDutyService.dto.EmployeeDutyDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmployeeDutyMapper {
    EmployeeDuty toEntity(EmployeeDutyDto employeeDutyDto);

    EmployeeDutyDto toDto(EmployeeDuty employeeDuty);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    EmployeeDuty partialUpdate(EmployeeDutyDto employeeDutyDto, @MappingTarget EmployeeDuty employeeDuty);
}