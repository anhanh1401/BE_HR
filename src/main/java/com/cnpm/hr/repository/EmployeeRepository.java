package com.cnpm.hr.repository;

import com.cnpm.hr.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface EmployeeRepository extends JpaRepository<Employees, Integer> {
    Optional<Employees> findFirstByEmployeeEmail(String employeeEmail);

    Employees findByEmployeeEmail(String employeeEmail);

}
