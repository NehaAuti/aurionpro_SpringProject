package com.aurionpro.dbconnect.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.dbconnect.entity.Employee;

import com.aurionpro.dbconnect.service.EmployeeService;

@RestController
@RequestMapping("/employeeapp")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployee()
	{
		return employeeService.getAllEmployee();
	}
	

	@PostMapping("/employees")
	public String addStudent(@RequestBody Employee employee)
	{
		employeeService.addEmployee(employee);
		return "Record added successfully"; 
	}

}
