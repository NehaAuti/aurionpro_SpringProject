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

import com.aurionpro.mappings.dto.SalaryAccountDto;
import com.aurionpro.mappings.entity.SalaryAccount;
import com.aurionpro.mappings.entity.SalaryTransaction;
import com.aurionpro.mappings.service.SalaryAccountService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/bankapp")
@RequiredArgsConstructor
public class SalaryAccountController {

	private final SalaryAccountService salaryAccountService;

	@PostMapping("/salaryaccount")
	public ResponseEntity<SalaryAccount> addSalaryAccount(@RequestBody SalaryAccountDto salaryAccountDto) {
		SalaryAccount salaryAccount = salaryAccountService.addSalaryAccount(salaryAccountDto);
		return ResponseEntity.ok(salaryAccount);
	}

	@GetMapping("/salaryaccount/{accountNumber}")
	public ResponseEntity<SalaryAccount> getSalaryAccountById(@PathVariable String accountNumber) {
		SalaryAccount salaryAccount = salaryAccountService.getSalaryAccountById(accountNumber);
		if (salaryAccount != null) {
			return ResponseEntity.ok(salaryAccount);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/salaryaccount")
	public ResponseEntity<List<SalaryAccount>> getAllSalaryAccounts() {
		List<SalaryAccount> salaryAccounts = salaryAccountService.getAllSalaryAccounts();
		return ResponseEntity.ok(salaryAccounts);
	}

	@PutMapping("/salaryaccount/{accountNumber}")
	public ResponseEntity<SalaryAccount> updateSalaryAccount(@PathVariable String accountNumber,
			@RequestBody SalaryAccountDto salaryAccountDto) {
		SalaryAccount salaryAccount = salaryAccountService.updateSalaryAccount(accountNumber, salaryAccountDto);
		if (salaryAccount != null) {
			return ResponseEntity.ok(salaryAccount);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/salaryaccount/{accountNumber}")
	public ResponseEntity<Void> deleteSalaryAccount(@PathVariable String accountNumber) {
		salaryAccountService.deleteSalaryAccount(accountNumber);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/salaryaccount/{accountNumber}")
	public ResponseEntity<SalaryAccount> allocateTransactions(@PathVariable String accountNumber,
			@RequestBody List<SalaryTransaction> salaryTransactions) {
		SalaryAccount salaryAccount = salaryAccountService.allocateTransactions(accountNumber, salaryTransactions);
		if (salaryAccount != null) {
			return ResponseEntity.ok(salaryAccount);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
