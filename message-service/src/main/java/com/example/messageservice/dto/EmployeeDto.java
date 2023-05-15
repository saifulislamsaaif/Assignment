package com.example.messageservice.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;


public record EmployeeDto(Integer id, String name, String phone, String email,
                          LocalDate joiningDate) implements Serializable {
}