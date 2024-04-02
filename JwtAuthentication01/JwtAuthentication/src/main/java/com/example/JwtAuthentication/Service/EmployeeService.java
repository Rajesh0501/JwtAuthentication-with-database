package com.example.JwtAuthentication.Service;

import com.example.JwtAuthentication.Entity.Employee;
import com.example.JwtAuthentication.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {
     @Autowired
    private EmployeeRepository employeeRepository;
     @Autowired
     private PasswordEncoder passwordEncoder;

//    List<Employee> employeeList = new ArrayList<>();
//    public EmployeeService(){
//        employeeList.add(new Employee(UUID.randomUUID().toString(),"rajesh","rajesh@123"));
//        employeeList.add(new Employee(UUID.randomUUID().toString(),"raj","raj@123"));
//        employeeList.add(new Employee(UUID.randomUUID().toString(),"nitin","nitin@123"));
//    }
//    public List<Employee> getEmployeeList(){
//        return employeeList;
//    }

    public List<Employee> getEmployeeList(){

        return employeeRepository.findAll();
    }

    public Employee createEmployee(Employee employee){
        employee.setId(UUID.randomUUID().toString());
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        return employeeRepository.save(employee);
    }
}
