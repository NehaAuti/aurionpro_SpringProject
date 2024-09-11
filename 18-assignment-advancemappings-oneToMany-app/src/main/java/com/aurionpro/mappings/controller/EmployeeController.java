package com.aurionpro.mappings.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aurionpro.mappings.entity.Employee;
import com.aurionpro.mappings.service.EmployeeService;

@RestController
@RequestMapping("/bankapp")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    
    @PostMapping("/employee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.addEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }


    @PostMapping("/employee/client")
    public ResponseEntity<Employee> addEmployeeToClient(
            @RequestParam int clientId,
            @RequestBody Employee employee) {
        Employee savedEmployee = employeeService.addEmployeeToClient(clientId, employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }
    
    @GetMapping("employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/employee")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }
    
    @PostMapping("employee/assignAccount")
    public ResponseEntity<Employee> assignAccountToEmployee(@RequestParam int employeeId, @RequestParam int accountNumber) {
        Employee updatedEmployee = employeeService.assignSalaryAccountToEmployee(employeeId, accountNumber);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

}
