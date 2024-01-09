package com.cnpm.hr.repository;

import com.cnpm.hr.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Roles, Integer> {
    @Query("select r from Roles r inner join EmployeeRole er on er.employeeId = :employeeId and er.roleId=r.roleId")
    List<Roles> getAllByEmployeeId(Integer employeeId);

    Roles getByRoleId(Integer roleId);

    @Query("select r from Roles r inner join EmployeeRole er on r.roleId = er.roleId where er.employeeId = :employeeId")
    List<Roles> getRolesForEmployeeEmail(Integer employeeId);
}
