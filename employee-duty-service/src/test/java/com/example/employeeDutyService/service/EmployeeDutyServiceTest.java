package com.example.employeeDutyService.service;

import com.example.employeeDutyService.domain.EmployeeDuty;
import com.example.employeeDutyService.dto.CreateEmployeeDutyDto;
import com.example.employeeDutyService.dto.EmployeeDutyDto;
import com.example.employeeDutyService.exception.EmployeeDutyNotFoundException;
import com.example.employeeDutyService.mapper.CreateEmployeeDutyMapper;
import com.example.employeeDutyService.mapper.EmployeeDutyMapper;
import com.example.employeeDutyService.repository.EmployeeDutyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class EmployeeDutyServiceTest {
    @Mock
    private EmployeeDutyRepository employeeDutyRepository;

    @Mock
    private CreateEmployeeDutyMapper createEmployeeDutyMapper;

    @Mock
    private EmployeeDutyMapper employeeDutyMapper;

    @Mock
    private ChangeEventProducer changeEventProducer;

    @InjectMocks
    private EmployeeDutyService employeeDutyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreateANewEmployeeDuty() {
        var dutyStartDate = LocalDate.now();
        var dutyEndDate = LocalDate.now().plusDays(3);

        var createEmployeeDutyDto = new CreateEmployeeDutyDto(1, dutyStartDate, dutyEndDate);

        var employeeDuty = EmployeeDuty
                .builder()
                .id(1)
                .employeeId(1)
                .dutyStart(dutyStartDate)
                .dutyEnd(dutyEndDate)
                .build();

        var employeeDutyDto = new EmployeeDutyDto(1, 1, dutyStartDate, dutyEndDate);

        given(createEmployeeDutyMapper.toEntity(createEmployeeDutyDto)).willReturn(employeeDuty);
        given(employeeDutyRepository.save(employeeDuty)).willReturn(employeeDuty);
        given(employeeDutyMapper.toDto(employeeDuty)).willReturn(employeeDutyDto);

        var createdEmployeeDuty = employeeDutyService.save(createEmployeeDutyDto);

        assertThat(createdEmployeeDuty).isNotNull();
        assertThat(createdEmployeeDuty).isEqualTo(employeeDutyDto);
    }

    @Test
    public void shouldUpdateAnEmployeeDuty() {
        var dutyStartDate = LocalDate.now();
        var dutyEndDate = LocalDate.now().plusDays(3);

        var employeeDtoForUpdating = new EmployeeDutyDto(1, 2, dutyStartDate, dutyEndDate);

        var existingEmployeeDuty = EmployeeDuty
                .builder()
                .id(1)
                .employeeId(1)
                .dutyStart(dutyStartDate)
                .dutyEnd(dutyEndDate)
                .build();

        var updatedEmployeeDuty = EmployeeDuty
                .builder()
                .id(1)
                .employeeId(2)
                .dutyStart(dutyStartDate)
                .dutyEnd(dutyEndDate)
                .build();

        when(employeeDutyRepository.findById(1)).thenReturn(Optional.of(existingEmployeeDuty));
        when(employeeDutyMapper.partialUpdate(employeeDtoForUpdating, existingEmployeeDuty))
                .thenReturn(updatedEmployeeDuty);
        when(employeeDutyRepository.save(updatedEmployeeDuty)).thenReturn(updatedEmployeeDuty);
        when(employeeDutyMapper.toDto(updatedEmployeeDuty)).thenReturn(employeeDtoForUpdating);

        var updatedEmployeeDTO = employeeDutyService.update(employeeDtoForUpdating);

        assertThat(updatedEmployeeDTO).isNotNull();
        assertThat(updatedEmployeeDTO).isEqualTo(employeeDtoForUpdating);
        assertThat(updatedEmployeeDTO.employeeId()).isEqualTo(employeeDtoForUpdating.employeeId());
    }

    @Test
    public void shouldNotUpdateWithInvalidId() {
        var dutyStartDate = LocalDate.now();
        var dutyEndDate = LocalDate.now().plusDays(3);

        var employeeDtoForUpdating = new EmployeeDutyDto(2, 1, dutyStartDate, dutyEndDate);

        when(employeeDutyRepository.findById(2))
                .thenThrow(new EmployeeDutyNotFoundException("employee duty not found with given id"));

        assertThatThrownBy(() -> employeeDutyService.update(employeeDtoForUpdating))
                .isInstanceOf(EmployeeDutyNotFoundException.class);
    }

    @Test
    public void shouldFetchListOfEmployeeDuties() {
        var firstDutyStartDate = LocalDate.now();
        var firstDutyEndDate = LocalDate.now().plusDays(3);

        var secondDutyStartDate = LocalDate.now();
        var secondDutyEndDate = LocalDate.now().plusDays(5);

        var employeeDuties = List.of(
                EmployeeDuty
                        .builder()
                        .id(1)
                        .employeeId(1)
                        .dutyStart(firstDutyStartDate)
                        .dutyEnd(firstDutyEndDate)
                        .build(),
                EmployeeDuty
                        .builder()
                        .id(2)
                        .dutyStart(secondDutyStartDate)
                        .dutyEnd(secondDutyEndDate)
                        .build()
        );

        var employeeDutyDtos = List.of(
                new EmployeeDutyDto(1, 1, firstDutyStartDate, firstDutyEndDate),
                new EmployeeDutyDto(2, 2, secondDutyStartDate, secondDutyEndDate)
        );

        when(employeeDutyRepository.findAll()).thenReturn(employeeDuties);
        when(employeeDutyMapper.toDto(employeeDuties.get(0))).thenReturn(employeeDutyDtos.get(0));
        when(employeeDutyMapper.toDto(employeeDuties.get(1))).thenReturn(employeeDutyDtos.get(1));

        var expectedEmpList = employeeDutyService.getAllEmployees();

        assertThat(expectedEmpList).isNotEmpty();
        assertThat(expectedEmpList).isEqualTo(employeeDutyDtos);
    }

    @Test
    public void shouldReturnAnEmployeeDuty() {
        var dutyStartDate = LocalDate.now();
        var dutyEndDate = LocalDate.now().plusDays(3);

        var employeeDutyFromDb = EmployeeDuty
                .builder()
                .id(1)
                .employeeId(1)
                .dutyStart(dutyStartDate)
                .dutyEnd(dutyEndDate)
                .build();

        var actualEmpDuty = new EmployeeDutyDto(1, 1, dutyStartDate, dutyEndDate);

        when(employeeDutyRepository.findById(1)).thenReturn(Optional.ofNullable(employeeDutyFromDb));
        when(employeeDutyMapper.toDto(employeeDutyFromDb)).thenReturn(actualEmpDuty);

        var expectedEmp = employeeDutyService.findOne(1);

        assertThat(expectedEmp).isNotNull();
        assertThat(expectedEmp).isEqualTo(actualEmpDuty);
    }

    @Test
    public void shouldDeleteAnEmployeeDuty() {

        when(employeeDutyRepository.existsById(1)).thenReturn(true);

        employeeDutyService.deleteOne(1);

        verify(employeeDutyRepository, times(1)).deleteById(1);
    }

    @Test
    public void shouldNotDeleteInvalidEmployeeDuty() {
        var exception = assertThrows(EmployeeDutyNotFoundException.class, () -> employeeDutyService.deleteOne(2));
        var expectedMsg = "Employee duty not found";
        var actualMsg = exception.getMessage();
        assertThat(actualMsg).isNotBlank().isEqualTo(expectedMsg);
    }
}