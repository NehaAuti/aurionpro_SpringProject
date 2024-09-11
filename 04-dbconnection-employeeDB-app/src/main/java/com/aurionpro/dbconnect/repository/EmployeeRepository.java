package com.aurionpro.dbconnect.repository;

import java.util.List;

import com.aurionpro.dbconnect.entity.Employee;

public interface EmployeeRepository {
	List<Employee> getAllEmployee();
	void addEmployee(Employee employee);
}
