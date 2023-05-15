package com.example.messageservice.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record EmployeeDutyDto(Integer id, Integer employeeId, LocalDate dutyStart,
                              LocalDate dutyEnd) implements Serializable {
}