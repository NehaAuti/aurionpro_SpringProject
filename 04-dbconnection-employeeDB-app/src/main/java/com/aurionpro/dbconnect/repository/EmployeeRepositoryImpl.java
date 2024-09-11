package com.aurionpro.dbconnect.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aurionpro.dbconnect.entity.Employee;


import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository{

	@Autowired
	private EntityManager manager;
	

	
	@Override
	public List<Employee> getAllEmployee() {
		TypedQuery<Employee> query = manager.createQuery("select e from Employee e",Employee.class);
		return query.getResultList();
	}



	@Override
	@Transactional
	public void addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		manager.merge(employee);
	}




	

}
