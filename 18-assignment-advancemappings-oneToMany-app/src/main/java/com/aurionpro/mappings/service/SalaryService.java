package com.aurionpro.mappings.service;

import java.util.List;

import com.aurionpro.mappings.entity.Salary;

public interface SalaryService {

	Salary addSalary(Salary salary);
	Salary getSalaryById(int salaryId);
	List<Salary> getAllSalaries();
	Salary updateSalary(Salary salary);
	void deleteSalary(int salaryId);
}
