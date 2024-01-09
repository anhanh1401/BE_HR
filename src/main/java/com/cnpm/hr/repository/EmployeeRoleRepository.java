package com.cnpm.hr.repository;

import com.cnpm.hr.entity.EmployeeRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

public interface EmployeeRoleRepository extends JpaRepository<EmployeeRole, Integer> {
    List<EmployeeRole> findAllByEmployeeId(Integer employeeId);

}
