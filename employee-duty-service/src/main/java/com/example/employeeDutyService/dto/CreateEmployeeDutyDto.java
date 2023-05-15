package com.example.employeeDutyService.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.example.employeeDutyService.domain.EmployeeDuty} entity
 */
public record CreateEmployeeDutyDto(@NotNull(message = "employee id must be provided") Integer employeeId,
                                    @NotNull(message = "a duty start date must be provided") LocalDate dutyStart,
                                    @NotNull(message = "a duty start date must be provided") LocalDate dutyEnd)
        implements Serializable {
}