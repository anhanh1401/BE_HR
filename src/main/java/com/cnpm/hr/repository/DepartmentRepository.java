package com.cnpm.hr.repository;

import com.cnpm.hr.entity.Departments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Departments, Integer> {
}
