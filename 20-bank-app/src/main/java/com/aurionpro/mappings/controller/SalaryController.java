package com.aurionpro.mappings.controller;

import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.mappings.dto.SalaryDto;
import com.aurionpro.mappings.entity.Salary;
import com.aurionpro.mappings.entity.SalaryTransaction;
import com.aurionpro.mappings.service.SalaryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/bankapp")
@RequiredArgsConstructor
public class SalaryController {

	private final SalaryService salaryService;

	@PostMapping("/salary")
	public ResponseEntity<Salary> addSalary(@RequestBody SalaryDto salaryDto) {
		Salary salary = salaryService.addSalary(salaryDto);
		return ResponseEntity.ok(salary);
	}

	@PutMapping("/salary/{salaryId}")
	public ResponseEntity<Salary> updateSalary(@PathVariable int salaryId, @RequestBody SalaryDto salaryDto) {
		Salary salary = salaryService.updateSalary(salaryId, salaryDto);
		if (salary != null) {
			return ResponseEntity.ok(salary);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/salary/{salaryId}")
	public ResponseEntity<Salary> getSalaryById(@PathVariable int salaryId) {
		Salary salary = salaryService.getSalaryById(salaryId);
		if (salary != null) {
			return ResponseEntity.ok(salary);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/salary")
	public ResponseEntity<List<Salary>> getAllSalaries() {
		List<Salary> salaries = salaryService.getAllSalaries();
		return ResponseEntity.ok(salaries);
	}

	@DeleteMapping("/salary/{salaryId}")
	public ResponseEntity<Void> deleteSalary(@PathVariable int salaryId) {
		salaryService.deleteSalary(salaryId);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/salary/{salaryId}")
	public ResponseEntity<Salary> allocateSalaryTransactions(@PathVariable int salaryId,
			@RequestBody List<SalaryTransaction> salaryTransactions) {
		Salary salary = salaryService.allocateSalaryTransactions(salaryId, salaryTransactions);
		if (salary != null) {
			return ResponseEntity.ok(salary);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
