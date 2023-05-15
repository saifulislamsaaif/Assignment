package com.example.messageservice.client;

import com.example.messageservice.dto.EmployeeDutyDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface EmployeeDutyClient {
    @GetExchange("/api/v1/employee-duties/{id}")
    EmployeeDutyDto findEmployeeDutyById(@PathVariable Integer id);
}
