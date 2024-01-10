package com.cnpm.hr.config;

import com.cnpm.hr.entity.Employees;
import com.cnpm.hr.entity.Roles;
import com.cnpm.hr.repository.EmployeeRepository;
import com.cnpm.hr.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private EmployeeRepository emRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String employeeEmail) throws UsernameNotFoundException {
        Employees em = emRepository.findByEmployeeEmail(employeeEmail);
        if(em == null){
            throw new UsernameNotFoundException("Email not found");
        }
        List<Roles> roles = roleRepository.getRolesForEmployeeEmail(em.getEmployeeId());
        List<GrantedAuthority> listAuthorities = roles.stream()
                .map(item->new SimpleGrantedAuthority(item.getRoleName()))
                .collect(Collectors.toList());
        return new UserDetailsImpl(em.getEmployeeEmail(), em.getPassword(), listAuthorities);
    }
}
