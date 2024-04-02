package com.example.JwtAuthentication.Service;

import com.example.JwtAuthentication.Entity.Employee;
import com.example.JwtAuthentication.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //load user from database
         Employee employee = employeeRepository.findByEmail(username).orElseThrow(()->new RuntimeException("user not found"));
         return employee;
    }
}
