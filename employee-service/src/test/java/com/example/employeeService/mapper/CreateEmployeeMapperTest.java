package com.example.employeeService.mapper;

import com.example.employeeService.dto.CreateEmployeeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CreateEmployeeMapperTest {

    @Autowired
    private CreateEmployeeMapper createEmployeeMapper;

    @Test
    public void createEmployeeDtoShouldMapToEmployee() {
        var createdEmployeeDTO = new CreateEmployeeDto("test",
                "1234456667", "test@test.com", LocalDate.now());
        var createdEmployee = createEmployeeMapper.toEntity(createdEmployeeDTO);

        assertThat(createdEmployee).isNotNull();
        assertThat(createdEmployee.getName()).isEqualTo(createdEmployeeDTO.name());
        assertThat(createdEmployee.getEmail()).isEqualTo(createdEmployeeDTO.email());
        assertThat(createdEmployee.getJoiningDate()).isEqualTo(createdEmployeeDTO.joiningDate());
    }

}