package com.example.employeeDutyService.controller;

import com.example.employeeDutyService.dto.CreateEmployeeDutyDto;
import com.example.employeeDutyService.dto.EmployeeDutyDto;
import com.example.employeeDutyService.service.EmployeeDutyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employee-duties")
public class EmployeeDutyResource {
    private final EmployeeDutyService employeeDutyService;

    @PostMapping
    ResponseEntity<EmployeeDutyDto> postEmployeeDuty(@Validated @RequestBody CreateEmployeeDutyDto employeeDutyDto) {
        var createdEmployeeDuty = employeeDutyService.save(employeeDutyDto);
        return new ResponseEntity<>(createdEmployeeDuty, HttpStatus.CREATED);
    }

    @PutMapping
    ResponseEntity<EmployeeDutyDto> putEmployeeDuty(@Validated @RequestBody EmployeeDutyDto employeeDto) {
        var updatedEmployeeDuty = employeeDutyService.update(employeeDto);
        return ResponseEntity.ok().body(updatedEmployeeDuty);
    }

    @GetMapping
    ResponseEntity<List<EmployeeDutyDto>> getEmployeeDuties() {
        var employeeDutyList = employeeDutyService.getAllEmployees();
        return ResponseEntity.ok().body(employeeDutyList);
    }

    @GetMapping("/{id}")
    ResponseEntity<EmployeeDutyDto> getEmployeeDuty(@PathVariable Integer id) {
        var employeeDuty = employeeDutyService.findOne(id);
        return ResponseEntity.ok().body(employeeDuty);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
        employeeDutyService.deleteOne(id);
        return ResponseEntity.ok().build();
    }
}
