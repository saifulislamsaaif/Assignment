package com.example.employeeService.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.example.employeeService.domain.Employee} entity
 */
public record EmployeeDto(Integer id, String name, String phone, String email,
                          LocalDate joiningDate) implements Serializable {
}