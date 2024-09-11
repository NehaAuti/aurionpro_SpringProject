package com.aurionpro.jpacurd.entity.service;

import java.util.List;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.aurionpro.jpacurd.entity.Employee;



public interface EmployeeService {
	Page<Employee> getAllEmployee(int pageno , int pagesize);
	void addEmployee(Employee employee);
	Optional<Employee> getByEmployeeId(int employeeId);
	void deleteEmployee(int employeeId);
	Page<Employee> getAllEmployeesByName(String firstName,int pageno,int pagesize);

}
