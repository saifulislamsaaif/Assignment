package com.example.employeeDutyService.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.example.employeeDutyService.domain.EmployeeDuty} entity
 */
public record EmployeeDutyDto(Integer id, Integer employeeId, LocalDate dutyStart,
                              LocalDate dutyEnd) implements Serializable {
}