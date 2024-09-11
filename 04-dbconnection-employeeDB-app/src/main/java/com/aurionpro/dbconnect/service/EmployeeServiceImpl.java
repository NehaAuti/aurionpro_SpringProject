package com.aurionpro.dbconnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.dbconnect.entity.Employee;
import com.aurionpro.dbconnect.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepo;


	@Override
	public List<Employee> getAllEmployee() {
		// TODO Auto-generated method stub
		return employeeRepo.getAllEmployee();
	}


	@Override
	public void addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		employeeRepo.addEmployee(employee);
	}




}

