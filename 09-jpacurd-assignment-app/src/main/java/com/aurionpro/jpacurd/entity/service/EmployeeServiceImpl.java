package com.aurionpro.jpacurd.entity.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.jpacurd.entity.Employee;
import com.aurionpro.jpacurd.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	private EmployeeRepository employeeRepo;

	@Override
	public Page<Employee> getAllEmployee(int pageno, int pagesize) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageno,pagesize);
		Page<Employee> employeePage = employeeRepo.findAll(pageable);
		return employeePage;
		//return employeeRepo.findAll();
	}

	@Override
	public void addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		employeeRepo.save(employee);
		
	}

	@Override
	public Optional<Employee> getByEmployeeId(int employeeId) {
		// TODO Auto-generated method stub
		return employeeRepo.findById(employeeId);
	}

	@Override
	public void deleteEmployee(int employeeId) {
		// TODO Auto-generated method stub
		employeeRepo.deleteById(employeeId);
		
	}

	@Override
	public Page<Employee> getAllEmployeesByName(String firstName, int pageno, int pagesize) {
	    Pageable pageable = PageRequest.of(pageno, pagesize);
	    return employeeRepo.findByFirstName(firstName, pageable);
	}


}
