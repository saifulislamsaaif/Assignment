package com.example.employeeService.service;

import com.example.employeeService.domain.Employee;
import com.example.employeeService.dto.CreateEmployeeDto;
import com.example.employeeService.dto.EmployeeDto;
import com.example.employeeService.exception.EmployeeNotFoundException;
import com.example.employeeService.mapper.CreateEmployeeMapper;
import com.example.employeeService.mapper.EmployeeMapper;
import com.example.employeeService.repository.EmployeeRepository;
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
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private CreateEmployeeMapper createEmployeeMapper;

    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreateANewEmployee() {
        var currentDate = LocalDate.now();
        var createEmployeeDto = new CreateEmployeeDto("test", "12345678", "test@test.com", currentDate);
        var employee = Employee
                .builder()
                .id(1)
                .name("test")
                .phone("12345678")
                .email("test@test.com")
                .joiningDate(currentDate)
                .build();
        var employeeDto = new EmployeeDto(1, "test", "12345678", "test@test.com", currentDate);
        given(createEmployeeMapper.toEntity(createEmployeeDto)).willReturn(employee);
        given(employeeRepository.save(employee)).willReturn(employee);
        given(employeeMapper.toDto(employee)).willReturn(employeeDto);

        var createdEmployee = employeeService.save(createEmployeeDto);

        assertThat(createdEmployee).isNotNull();
        assertThat(createdEmployee).isEqualTo(employeeDto);
    }

    @Test
    public void shouldUpdateAnEmployee() {
        var currentDate = LocalDate.now();

        var employeeDtoForUpdating = new EmployeeDto(1, "updated_test", "12345678",
                "test@test.com", currentDate);

        var existingEmployee = Employee
                .builder()
                .id(1)
                .name("test")
                .phone("12345678")
                .email("test@test.com")
                .joiningDate(currentDate)
                .build();

        var updatedEmployee = Employee
                .builder()
                .id(1)
                .name("updated_test")
                .phone("12345678")
                .email("test@test.com")
                .joiningDate(currentDate)
                .build();

        when(employeeRepository.findById(1)).thenReturn(Optional.of(existingEmployee));
        when(employeeMapper.partialUpdate(employeeDtoForUpdating, existingEmployee)).thenReturn(updatedEmployee);
        when(employeeRepository.save(updatedEmployee)).thenReturn(updatedEmployee);
        when(employeeMapper.toDto(updatedEmployee)).thenReturn(employeeDtoForUpdating);

        var updatedEmployeeDTO = employeeService.update(employeeDtoForUpdating);

        assertThat(updatedEmployeeDTO).isNotNull();
        assertThat(updatedEmployeeDTO).isEqualTo(employeeDtoForUpdating);
        assertThat(updatedEmployeeDTO.name()).isEqualTo(employeeDtoForUpdating.name());
    }

    @Test
    public void shouldNotUpdateWithInvalidId(){
        var currentDate = LocalDate.now();

        var employeeDtoForUpdating = new EmployeeDto(1, "updated_test", "12345678",
                "test@test.com", currentDate);

        when(employeeRepository.findById(2))
                .thenThrow(new EmployeeNotFoundException("employee not found with given id"));

        assertThatThrownBy(() -> employeeService.update(employeeDtoForUpdating))
                .isInstanceOf(EmployeeNotFoundException.class);
    }

    @Test
    public void shouldFetchListOfEmployees(){
        var firstEmpJoiningDate = LocalDate.now();
        var secondEmpJoiningDate = LocalDate.now();

        var employees = List.of(
                Employee
                        .builder()
                        .id(1)
                        .name("test 1")
                        .phone("313141341")
                        .email("test1@test.com")
                        .joiningDate(firstEmpJoiningDate)
                        .build(),
                Employee
                        .builder()
                        .id(2)
                        .name("test 2")
                        .phone("189712314")
                        .email("test2@test.com")
                        .joiningDate(secondEmpJoiningDate)
                        .build()
        );

        var employeeDtos = List.of(
                new EmployeeDto(1, "test 1", "313141341",
                        "test1@test.com", firstEmpJoiningDate),
                new EmployeeDto(2, "test 2", "189712314",
                        "test2@test.com", secondEmpJoiningDate)
        );

        when(employeeRepository.findAll()).thenReturn(employees);
        when(employeeMapper.toDto(employees.get(0))).thenReturn(employeeDtos.get(0));
        when(employeeMapper.toDto(employees.get(1))).thenReturn(employeeDtos.get(1));

        var expectedEmpList = employeeService.getAllEmployees();

        assertThat(expectedEmpList).isNotEmpty();
        assertThat(expectedEmpList).isEqualTo(employeeDtos);
    }

    @Test
    public void shouldReturnAnEmployee(){
        var joiningDate = LocalDate.now();

        var employeeFromDb = Employee
                .builder()
                .id(1)
                .name("test 1")
                .phone("313141341")
                .email("test1@test.com")
                .joiningDate(joiningDate)
                .build();

        var actualEmp = new EmployeeDto(1, "test 1", "313141341",
                "test1@test.com", joiningDate);

        when(employeeRepository.findById(1)).thenReturn(Optional.ofNullable(employeeFromDb));
        when(employeeMapper.toDto(employeeFromDb)).thenReturn(actualEmp);

        var expectedEmp = employeeService.findOne(1);

        assertThat(expectedEmp).isNotNull();
        assertThat(expectedEmp).isEqualTo(actualEmp);
    }

    @Test
    public void shouldDeleteAnEmployee(){
        var joiningDate = LocalDate.now();

        var employeeFromDb = Employee
                .builder()
                .id(1)
                .name("test 1")
                .phone("313141341")
                .email("test1@test.com")
                .joiningDate(joiningDate)
                .build();

        when(employeeRepository.findById(1)).thenReturn(Optional.ofNullable(employeeFromDb));
        when(employeeRepository.existsById(1)).thenReturn(true);

        employeeService.deleteOne(1);

        assert employeeFromDb != null;
        verify(employeeRepository, times(1)).deleteById(1);
    }
}