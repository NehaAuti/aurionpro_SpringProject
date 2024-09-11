package com.aurionpro.dbconnect.service;

import java.util.List;

import com.aurionpro.dbconnect.entity.Employee;



public interface EmployeeService{
	List<Employee> getAllEmployee();
    void addEmployee(Employee employee);
}
