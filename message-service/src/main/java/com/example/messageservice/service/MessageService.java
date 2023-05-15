package com.example.messageservice.service;

import com.example.messageservice.client.EmployeeClient;
import com.example.messageservice.client.EmployeeDutyClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageService {

    private final EmployeeDutyClient employeeDutyClient;
    private final EmployeeClient employeeClient;
    void sendMessage(Integer dutyId) {
        var employeeDuty = employeeDutyClient.findEmployeeDutyById(dutyId);
        var employee = employeeClient.findEmployeeById(employeeDuty.employeeId());
        log.info("Duty has been added or updated for {}. Employee's phone: {} and email: {}", employee.id(),
                employee.phone(), employee.email());
    }
}
