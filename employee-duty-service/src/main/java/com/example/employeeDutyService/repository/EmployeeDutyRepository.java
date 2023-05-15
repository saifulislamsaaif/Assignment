package com.example.employeeDutyService.repository;

import com.example.employeeDutyService.domain.EmployeeDuty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDutyRepository extends JpaRepository<EmployeeDuty, Integer> {
}