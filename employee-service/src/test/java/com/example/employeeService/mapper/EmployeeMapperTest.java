package com.example.employeeService.mapper;

import com.example.employeeService.domain.Employee;
import com.example.employeeService.dto.EmployeeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeMapperTest {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    public void employeeShouldMapToEmployeeDto() {
        var employee = Employee
                .builder()
                .id(1)
                .name("test")
                .phone("12345678")
                .email("test@test.com")
                .joiningDate(LocalDate.now())
                .build();

        var employeeDto = employeeMapper.toDto(employee);

        assertThat(employeeDto).isNotNull();

        assertThat(employeeDto.id())
                .isNotNull()
                .isEqualTo(employee.getId());

        assertThat(employeeDto.name())
                .isNotBlank()
                .isEqualTo(employee.getName());

        assertThat(employeeDto.email())
                .isNotBlank()
                .isEqualTo(employee.getEmail());

        assertThat(employeeDto.phone())
                .isNotBlank()
                .isEqualTo(employee.getPhone());

        assertThat(employeeDto.joiningDate())
                .isNotNull()
                .isEqualTo(employee.getJoiningDate());
    }

    @Test
    public void employeeDtoShouldMapToEmployee() {
        var employeeDto = new EmployeeDto(1, "test", "12345678",
                "test@test.com", LocalDate.now());

        var employee = employeeMapper.toEntity(employeeDto);

        assertThat(employee).isNotNull();

        assertThat(employee.getId())
                .isNotNull()
                .isEqualTo(employeeDto.id());

        assertThat(employee.getName())
                .isNotBlank()
                .isEqualTo(employeeDto.name());

        assertThat(employee.getEmail())
                .isNotBlank()
                .isEqualTo(employeeDto.email());

        assertThat(employee.getPhone())
                .isNotBlank()
                .isEqualTo(employeeDto.phone());

        assertThat(employee.getJoiningDate())
                .isNotNull()
                .isEqualTo(employeeDto.joiningDate());
    }

    @Test
    public void employeeDtoShouldPartiallyUpdateEmployee(){
        var currentDateTime = LocalDate.now();

        var employee = Employee
                .builder()
                .id(1)
                .name("test")
                .phone("12345678")
                .email("test@test.com")
                .joiningDate(currentDateTime)
                .build();

        var employeeDto = new EmployeeDto(1, "newTest", "12345678",
                "newTest@test.com", currentDateTime);

        var updatedEmployee = employeeMapper.partialUpdate(employeeDto, employee);

        assertThat(updatedEmployee).isNotNull();

        assertThat(updatedEmployee.getId())
                .isNotNull()
                .isEqualTo(employeeDto.id());

        assertThat(updatedEmployee.getName())
                .isNotBlank()
                .isEqualTo(employeeDto.name());

        assertThat(updatedEmployee.getEmail())
                .isNotBlank()
                .isEqualTo(employeeDto.email());

        assertThat(updatedEmployee.getPhone())
                .isNotBlank()
                .isEqualTo(employeeDto.phone());

        assertThat(updatedEmployee.getJoiningDate())
                .isNotNull()
                .isEqualTo(employeeDto.joiningDate());
    }

}