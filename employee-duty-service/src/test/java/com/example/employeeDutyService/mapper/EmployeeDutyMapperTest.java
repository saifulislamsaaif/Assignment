package com.example.employeeDutyService.mapper;

import com.example.employeeDutyService.domain.EmployeeDuty;
import com.example.employeeDutyService.dto.EmployeeDutyDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeDutyMapperTest {
    @Autowired
    private EmployeeDutyMapper mapper;

    @Test
    public void employeeDutyShouldMapToEmployeeDutyDto() {
        var dutyStartDate = LocalDate.now();
        var dutyEndDate = LocalDate.now().plusDays(3);

        var employeeDuty = EmployeeDuty
                .builder()
                .id(1)
                .employeeId(1)
                .dutyStart(dutyStartDate)
                .dutyEnd(dutyEndDate)
                .build();

        var employeeDutyDto = mapper.toDto(employeeDuty);

        assertThat(employeeDutyDto).isNotNull();

        assertThat(employeeDutyDto.id())
                .isNotNull()
                .isEqualTo(employeeDuty.getId());

        assertThat(employeeDutyDto.employeeId())
                .isEqualTo(employeeDuty.getEmployeeId());

        assertThat(employeeDutyDto.dutyStart())
                .isNotNull()
                .isEqualTo(employeeDuty.getDutyStart());

        assertThat(employeeDutyDto.dutyEnd())
                .isNotNull()
                .isEqualTo(employeeDuty.getDutyEnd());
    }

    @Test
    public void employeeDutyDtoShouldMapToEmployeeDuty() {
        var dutyStartDate = LocalDate.now();
        var dutyEndDate = LocalDate.now().plusDays(3);


        var employeeDutyDto = new EmployeeDutyDto(1,1, dutyStartDate, dutyEndDate);

        var employeeDuty = mapper.toEntity(employeeDutyDto);

        assertThat(employeeDuty).isNotNull();

        assertThat(employeeDuty.getId())
                .isNotNull()
                .isEqualTo(employeeDutyDto.id());

        assertThat(employeeDuty.getEmployeeId())
                .isEqualTo(employeeDutyDto.employeeId());

        assertThat(employeeDuty.getDutyStart())
                .isNotNull()
                .isEqualTo(employeeDutyDto.dutyStart());

        assertThat(employeeDuty.getDutyEnd())
                .isNotNull()
                .isEqualTo(employeeDutyDto.dutyEnd());
    }

    @Test
    public void employeeDutyDtoShouldPartiallyUpdateEmployeeDuty(){
        var dutyStartDate = LocalDate.now();
        var dutyEndDate = LocalDate.now().plusDays(3);

        var employeeDuty = EmployeeDuty
                .builder()
                .id(1)
                .employeeId(1)
                .dutyStart(dutyStartDate)
                .dutyEnd(dutyEndDate)
                .build();

        var employeeDutyDto = new EmployeeDutyDto(1,2, dutyStartDate, dutyEndDate);

        var updatedEmployeeDuty = mapper.partialUpdate(employeeDutyDto, employeeDuty);

        assertThat(updatedEmployeeDuty).isNotNull();

        assertThat(updatedEmployeeDuty.getId())
                .isNotNull()
                .isEqualTo(employeeDutyDto.id());

        assertThat(updatedEmployeeDuty.getEmployeeId())
                .isNotNull()
                .isEqualTo(employeeDutyDto.employeeId());

        assertThat(updatedEmployeeDuty.getDutyStart())
                .isNotNull()
                .isEqualTo(employeeDutyDto.dutyStart());

        assertThat(updatedEmployeeDuty.getDutyEnd())
                .isNotNull()
                .isEqualTo(employeeDutyDto.dutyEnd());
    }
}