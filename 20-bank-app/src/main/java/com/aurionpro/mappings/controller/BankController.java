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

import com.aurionpro.mappings.dto.BankDto;
import com.aurionpro.mappings.entity.Bank;
import com.aurionpro.mappings.entity.SalaryAccount;
import com.aurionpro.mappings.service.BankService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/bankapp")
@RequiredArgsConstructor
public class BankController {

	private final BankService bankService;

	@PostMapping("/bank")
	public ResponseEntity<Bank> addBank(@RequestBody BankDto bankDto) {
		Bank bank = bankService.addBank(bankDto);
		return ResponseEntity.ok(bank);
	}

	@PutMapping("/bank/{bankId}")
	public ResponseEntity<Bank> updateBank(@PathVariable int bankId, @RequestBody BankDto bankDto) {
		Bank bank = bankService.updateBank(bankId, bankDto);
		if (bank != null) {
			return ResponseEntity.ok(bank);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/bank/{bankId}")
	public ResponseEntity<Void> deleteBank(@PathVariable int bankId) {
		bankService.deleteBank(bankId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/bank/{bankId}")
	public ResponseEntity<Bank> getBankById(@PathVariable int bankId) {
		Bank bank = bankService.getBankById(bankId);
		if (bank != null) {
			return ResponseEntity.ok(bank);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/bank")
	public ResponseEntity<List<Bank>> getAllBanks() {
		List<Bank> banks = bankService.getAllBanks();
		return ResponseEntity.ok(banks);
	}

	@PostMapping("bank/{bankId}/salary-accounts")
	public ResponseEntity<Bank> allocateSalaryAccounts(@PathVariable int bankId,
			@RequestBody List<SalaryAccount> salaryAccounts) {
		Bank bank = bankService.allocateSalaryAccounts(bankId, salaryAccounts);
		if (bank != null) {
			return ResponseEntity.ok(bank);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
