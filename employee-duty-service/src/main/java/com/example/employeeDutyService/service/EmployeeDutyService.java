package com.example.employeeDutyService.service;

import com.example.employeeDutyService.domain.EmployeeDuty;
import com.example.employeeDutyService.dto.CreateEmployeeDutyDto;
import com.example.employeeDutyService.dto.EmployeeDutyDto;
import com.example.employeeDutyService.exception.EmployeeDutyNotFoundException;
import com.example.employeeDutyService.mapper.CreateEmployeeDutyMapper;
import com.example.employeeDutyService.mapper.EmployeeDutyMapper;
import com.example.employeeDutyService.repository.EmployeeDutyRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class EmployeeDutyService {

    private final EmployeeDutyRepository employeeDutyRepository;
    private final EmployeeDutyMapper employeeDutyMapper;
    private final CreateEmployeeDutyMapper createEmployeeDutyMapper;
    private final ChangeEventProducer changeEventProducer;

    public EmployeeDutyDto save(CreateEmployeeDutyDto employeeDutyDto) {
        log.debug(employeeDutyDto.toString());
        EmployeeDuty createdEmployeeDuty = employeeDutyRepository
                .save(createEmployeeDutyMapper.toEntity(employeeDutyDto));

        changeEventProducer.sendEvent(createdEmployeeDuty.getId());
        return employeeDutyMapper.toDto(createdEmployeeDuty);
    }

    public EmployeeDutyDto update(EmployeeDutyDto employeeDto) {
        return employeeDutyRepository.findById(employeeDto.id())
                .map(employee -> employeeDutyMapper.partialUpdate(employeeDto, employee))
                .map(employeeDutyRepository::save)
                .map(employeeDutyMapper::toDto)
                .map(employeeDutyDto -> {
                    changeEventProducer.sendEvent(employeeDutyDto.id());
                    return employeeDutyDto;
                })
                .orElseThrow(() -> new EmployeeDutyNotFoundException("Employee duty not found"));
    }

    public List<EmployeeDutyDto> getAllEmployees() {
        return employeeDutyRepository.findAll()
                .stream()
                .map(employeeDutyMapper::toDto)
                .collect(Collectors.toList());
    }

    public EmployeeDutyDto findOne(Integer id) {
        return employeeDutyRepository.findById(id)
                .map(employeeDutyMapper::toDto)
                .orElseThrow(() -> new EmployeeDutyNotFoundException("Employee duty not found"));
    }

    public void deleteOne(Integer id) {
        if (!employeeDutyRepository.existsById(id)) {
            throw new EmployeeDutyNotFoundException("Employee duty not found");
        }
        employeeDutyRepository.deleteById(id);
    }
}
