package com.aurionpro.mappings.service;

import java.util.List;

import com.aurionpro.mappings.entity.Employee;

public interface EmployeeService {
	    Employee addEmployee(Employee employee);
	    Employee addEmployeeToClient(int clientId, Employee employee);
	    Employee getEmployeeById(int employeeId);
	    List<Employee> getAllEmployees();
	    Employee assignSalaryAccountToEmployee(int employeeId, int accountNumber);

}
