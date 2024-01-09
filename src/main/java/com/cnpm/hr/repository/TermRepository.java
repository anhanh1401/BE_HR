package com.cnpm.hr.repository;

import com.cnpm.hr.entity.Terms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermRepository extends JpaRepository<Terms, Integer> {
}
