package com.example.employeeDutyService.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "employee_duty")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDuty {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_duty_seq")
    @SequenceGenerator(name = "employee_duty_seq")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "employee_id", nullable = false)
    private Integer employeeId;

    @Column(name = "duty_start", nullable = false)
    private LocalDate dutyStart;

    @Column(name = "duty_end", nullable = false)
    private LocalDate dutyEnd;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDuty that = (EmployeeDuty) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getEmployeeId(), that.getEmployeeId()) &&
                Objects.equals(getDutyStart(), that.getDutyStart()) && Objects.equals(getDutyEnd(), that.getDutyEnd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmployeeId(), getDutyStart(), getDutyEnd());
    }

    @Override
    public String toString() {
        return "EmployeeDuty{" +
                "id=" + id +
                ", employeeId=" + employeeId +
                ", dutyStart=" + dutyStart +
                ", dutyEnd=" + dutyEnd +
                '}';
    }
}