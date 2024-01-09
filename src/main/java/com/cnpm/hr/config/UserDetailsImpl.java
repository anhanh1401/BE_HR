package com.cnpm.hr.config;

import com.cnpm.hr.entity.EmployeeRole;
import com.cnpm.hr.entity.Employees;
import com.cnpm.hr.entity.Roles;
import com.cnpm.hr.repository.EmployeeRoleRepository;
import com.cnpm.hr.repository.RoleRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {

    private static EmployeeRoleRepository emroleRepo;
    private static RoleRepository roleRepo;
    private static EmployeeRole emrole;
    private static Roles role;
    private String employeeEmail;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(String employeeEmail, String password, Collection<? extends GrantedAuthority> authorities) {
        this.employeeEmail = employeeEmail;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsImpl mapUserToUserDetail(Employees em){
        List<EmployeeRole> roles = emroleRepo.findAllByEmployeeId(em.getEmployeeId());
        List<GrantedAuthority> listAuthorities = emroleRepo.findAllByEmployeeId(em.getEmployeeId()).stream()
                .map(role -> new SimpleGrantedAuthority(roleRepo.getByRoleId(emrole.getRoleId()).getRoleName()))
                .collect(Collectors.toList());
        return new UserDetailsImpl(
                em.getEmployeeEmail(),
                em.getPassword(),
                listAuthorities
        );
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return employeeEmail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
