package com.cnpm.hr.repository;

import com.cnpm.hr.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance,Integer> {
}
