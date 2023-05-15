package com.example.employeeService.service;

import com.example.employeeService.domain.Employee;
import com.example.employeeService.dto.CreateEmployeeDto;
import com.example.employeeService.dto.EmployeeDto;
import com.example.employeeService.exception.EmployeeNotFoundException;
import com.example.employeeService.mapper.CreateEmployeeMapper;
import com.example.employeeService.mapper.EmployeeMapper;
import com.example.employeeService.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final CreateEmployeeMapper createEmployeeMapper;
    private final EmployeeMapper employeeMapper;

    public EmployeeDto save(CreateEmployeeDto employeeInfo) {
        Employee createdEmployee = employeeRepository
                .save(createEmployeeMapper.toEntity(employeeInfo));

        return employeeMapper.toDto(createdEmployee);
    }

    public EmployeeDto update(EmployeeDto employeeDto) {
        return employeeRepository.findById(employeeDto.id())
                .map(employee -> employeeMapper.partialUpdate(employeeDto, employee))
                .map(employeeRepository::save)
                .map(employeeMapper::toDto)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
    }

    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }

    public EmployeeDto findOne(Integer id) {
        return employeeRepository.findById(id)
                .map(employeeMapper::toDto)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
    }

    public void deleteOne(Integer id){
        if (!employeeRepository.existsById(id)){
            throw new EmployeeNotFoundException("Employee not found");
        }
        employeeRepository.deleteById(id);
    }
}
