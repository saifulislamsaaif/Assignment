package com.example.employeeService.controller;

import com.example.employeeService.dto.CreateEmployeeDto;
import com.example.employeeService.dto.EmployeeDto;
import com.example.employeeService.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employees")
@Slf4j
public class EmployeeResource {
    private final EmployeeService employeeService;

    @PostMapping
    ResponseEntity<EmployeeDto> postEmployee(@RequestBody @Valid CreateEmployeeDto employeeDto) {
        log.debug(employeeDto.toString());
        var createdEmployee = employeeService.save(employeeDto);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    @PutMapping
    ResponseEntity<EmployeeDto> putEmployee(@RequestBody @Validated EmployeeDto employeeDto) {
        var createdEmployee = employeeService.update(employeeDto);
        return ResponseEntity.ok().body(createdEmployee);
    }

    @GetMapping
    ResponseEntity<List<EmployeeDto>> getEmployees() {
        var employeeList = employeeService.getAllEmployees();
        return ResponseEntity.ok().body(employeeList);
    }

    @GetMapping("/{id}")
    ResponseEntity<EmployeeDto> getEmployee(@PathVariable Integer id) {
        var employee = employeeService.findOne(id);
        return ResponseEntity.ok().body(employee);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteOne(id);
        return ResponseEntity.ok().build();
    }
}
