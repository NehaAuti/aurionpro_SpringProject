package com.aurionpro.mappings.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aurionpro.mappings.entity.Employee;
import com.aurionpro.mappings.service.EmployeeService;
import com.aurionpro.mappings.dto.EmployeeDTO;
import com.aurionpro.mappings.dto.PageResponse;
import com.aurionpro.mappings.dto.SalaryAccountDTO;
import com.aurionpro.mappings.error.ResourceNotFoundException;

@RestController
@RequestMapping("/employeeapp")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employees")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.addEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/employee")
    public ResponseEntity<PageResponse<Employee>> getAllEmployees(
            @RequestParam int pageNo,
            @RequestParam int pageSize) {
        PageResponse<Employee> employeesPage = employeeService.getAllEmployees(pageNo, pageSize);
        return new ResponseEntity<>(employeesPage, HttpStatus.OK);
    }

    @GetMapping("employee/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable int employeeId) {
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(employeeId);
        if (employeeDTO != null) {
            return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Employee not found with ID: " + employeeId);
        }
    }
    

    @GetMapping("employee/salaryaccount/{employeeId}")
    public ResponseEntity<SalaryAccountDTO> getEmployeeSalaryAccountById(@PathVariable int employeeId) {
        SalaryAccountDTO salaryAccountDTO = employeeService.getEmployeeSalaryAccountById(employeeId);
        if (salaryAccountDTO != null) {
            return new ResponseEntity<>(salaryAccountDTO, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Salary account not found with ID: " + employeeId);
        }
    }

    @PostMapping("employee/{employeeId}")
    public ResponseEntity<Void> partiallyUpdateEmployee(
    		@PathVariable int employeeId,
            @RequestBody EmployeeDTO employeeDTO) {
        boolean updated = employeeService.partiallyUpdateEmployee(employeeId, employeeDTO);
        if (updated) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Employee not found with ID: " + employeeId);
        }
    }
}