package com.example.employeeDutyService.mapper;

import com.example.employeeDutyService.dto.CreateEmployeeDutyDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CreateEmployeeDutyMapperTest {

    @Autowired
    private CreateEmployeeDutyMapper mapper;

    @Test
    public void createEmployeeDutyDtoShouldMapToEmployeeDuty() {

        var dutyStartDate = LocalDate.now();
        var dutyEndDate = LocalDate.now().plusDays(3);

        var createdEmployeeDTO = new CreateEmployeeDutyDto(1, dutyStartDate, dutyEndDate);
        var createdEmployee = mapper.toEntity(createdEmployeeDTO);

        assertThat(createdEmployee).isNotNull();
        assertThat(createdEmployee.getEmployeeId()).isEqualTo(createdEmployeeDTO.employeeId());
        assertThat(createdEmployee.getDutyStart()).isEqualTo(createdEmployeeDTO.dutyStart());
        assertThat(createdEmployee.getDutyEnd()).isEqualTo(createdEmployeeDTO.dutyEnd());
    }

}