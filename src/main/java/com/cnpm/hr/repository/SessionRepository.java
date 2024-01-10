package com.cnpm.hr.repository;

import com.cnpm.hr.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Integer> {
    Optional<Session> findByEmployeeEmail(String employeeEmail);

}
