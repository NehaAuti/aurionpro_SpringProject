package com.aurionpro.mappings.service;

import com.aurionpro.mappings.dto.EmployeeDTO;
import com.aurionpro.mappings.dto.PageResponse;
import com.aurionpro.mappings.dto.SalaryAccountDTO;
import com.aurionpro.mappings.entity.Employee;

public interface EmployeeService {
	Employee addEmployee(Employee employee);
	PageResponse<Employee> getAllEmployees(int pageno, int pagesize);
	EmployeeDTO getEmployeeById(int employeeId);
	SalaryAccountDTO getEmployeeSalaryAccountById(int employeeId);
	boolean partiallyUpdateEmployee(int employeeId, EmployeeDTO employeeDTO);

}
