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

import com.aurionpro.mappings.dto.EmployeeDto;
import com.aurionpro.mappings.entity.Employee;
import com.aurionpro.mappings.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/bankapp")
@RequiredArgsConstructor
public class EmployeeController {

	private final EmployeeService employeeService;

	@PostMapping("/employee")
	public ResponseEntity<Employee> addEmployee(@RequestBody EmployeeDto employeeDto) {
		Employee employee = employeeService.addEmployee(employeeDto);
		return ResponseEntity.ok(employee);
	}

	@PutMapping("/employee/{employeeId}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable int employeeId, @RequestBody EmployeeDto employeeDto) {
		Employee employee = employeeService.updateEmployee(employeeId, employeeDto);
		if (employee != null) {
			return ResponseEntity.ok(employee);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/employee/{employeeId}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable int employeeId) {
		Employee employee = employeeService.getEmployeeById(employeeId);
		if (employee != null) {
			return ResponseEntity.ok(employee);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/employee")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employees = employeeService.getAllEmployees();
		return ResponseEntity.ok(employees);
	}

	@DeleteMapping("/employee/{employeeId}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable int employeeId) {
		employeeService.deleteEmployee(employeeId);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/employee/{employeeId}/salary-account/{accountNumber}")
	public ResponseEntity<Employee> assignSalaryAccount(@PathVariable int employeeId,
			@PathVariable String accountNumber) {
		Employee employee = employeeService.assignSalaryAccount(employeeId, accountNumber);
		if (employee != null) {
			return ResponseEntity.ok(employee);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/employee/{employeeId}/client/{clientId}")
	public ResponseEntity<Employee> assignClient(@PathVariable int employeeId, @PathVariable int clientId) {
		Employee employee = employeeService.assignClient(employeeId, clientId);
		if (employee != null) {
			return ResponseEntity.ok(employee);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
