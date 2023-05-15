package com.example.messageservice.client;

import com.example.messageservice.dto.EmployeeDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface EmployeeClient {
    @GetExchange("/api/v1/employees/{id}")
    EmployeeDto findEmployeeById(@PathVariable Integer id);
}
