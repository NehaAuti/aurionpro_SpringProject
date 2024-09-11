package com.aurionpro.mappings.service;


import java.util.List;

import com.aurionpro.mappings.dto.EmployeeDto;
import com.aurionpro.mappings.entity.Employee;

public interface EmployeeService {
    Employee addEmployee(EmployeeDto employeeDto);
    Employee updateEmployee(int employeeId, EmployeeDto employeeDto);
    void deleteEmployee(int employeeId);
    Employee getEmployeeById(int employeeId);
    List<Employee> getAllEmployees();
    Employee assignSalaryAccount(int employeeId, String salaryAccountNumber);
    Employee assignClient(int employeeId, int clientId);
}
