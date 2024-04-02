package com.example.JwtAuthentication.Controller;

import com.example.JwtAuthentication.Entity.Employee;
import com.example.JwtAuthentication.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/welcome")
public class HomeController {
   @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee")
    public List<Employee> getEmployee(){
        return employeeService.getEmployeeList();
    }
}
