package com.example.employeeService.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.example.employeeService.domain.Employee} entity
 */
public record   CreateEmployeeDto(@NotBlank(message = "name can not be empty") String name,
                                @NotBlank(message = "phone number can not be empty")
                                @Pattern(regexp = "^0\\d{2}-\\d{3}-\\d{4}$",
                                        message = "a valid phone number must be provided") String phone,
                                @NotBlank(message = "email can not be empty")
                                @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
                                        message = "a valid email address should be provided") String email,
                                @NotNull(message = "joining date can not be empty") LocalDate joiningDate)
        implements Serializable {
}