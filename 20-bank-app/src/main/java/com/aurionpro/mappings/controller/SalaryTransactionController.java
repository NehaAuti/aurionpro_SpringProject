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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.mappings.dto.SalaryTransactionDto;
import com.aurionpro.mappings.entity.SalaryTransaction;
import com.aurionpro.mappings.service.SalaryTransactionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/bankapp")
@RequiredArgsConstructor
public class SalaryTransactionController {

	private final SalaryTransactionService salaryTransactionService;

	@PostMapping("/salarytransaction")
	public ResponseEntity<SalaryTransaction> addSalaryTransaction(@RequestParam String accountNumber,
			@RequestBody SalaryTransactionDto salaryTransactionDto) {
		SalaryTransaction salaryTransaction = salaryTransactionService.addSalaryTransaction(accountNumber,
				salaryTransactionDto);
		return ResponseEntity.ok(salaryTransaction);
	}

	@GetMapping("/salarytransaction/{transactionId}")
	public ResponseEntity<SalaryTransaction> getSalaryTransactionById(@PathVariable int transactionId) {
		SalaryTransaction salaryTransaction = salaryTransactionService.getSalaryTransactionById(transactionId);
		if (salaryTransaction != null) {
			return ResponseEntity.ok(salaryTransaction);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/salarytransaction")
	public ResponseEntity<List<SalaryTransaction>> getAllSalaryTransactions() {
		List<SalaryTransaction> salaryTransactions = salaryTransactionService.getAllSalaryTransactions();
		return ResponseEntity.ok(salaryTransactions);
	}

	@PutMapping("/salarytransaction/{transactionId}")
	public ResponseEntity<SalaryTransaction> updateSalaryTransaction(@PathVariable int transactionId,
			@RequestBody SalaryTransactionDto salaryTransactionDto) {
		SalaryTransaction salaryTransaction = salaryTransactionService.updateSalaryTransaction(transactionId,
				salaryTransactionDto);
		if (salaryTransaction != null) {
			return ResponseEntity.ok(salaryTransaction);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("salarytransaction/{transactionId}")
	public ResponseEntity<Void> deleteSalaryTransaction(@PathVariable int transactionId) {
		salaryTransactionService.deleteSalaryTransaction(transactionId);
		return ResponseEntity.noContent().build();
	}
}
