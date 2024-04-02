package com.example.JwtAuthentication.Repository;

import com.example.JwtAuthentication.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,String> {
    public Optional<Employee> findByEmail(String email);
}
