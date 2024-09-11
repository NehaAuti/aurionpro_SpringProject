package com.aurionpro.mappings.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aurionpro.mappings.dto.SalaryAccountDto;
import com.aurionpro.mappings.entity.SalaryAccount;
import com.aurionpro.mappings.service.SalaryAccountService;

@RestController
@RequestMapping("/bankapp")
public class SalaryAccountController {

	@Autowired
    private SalaryAccountService salaryAccountService;

	 // Endpoint to create a new SalaryAccount
	 @PostMapping("/salaryaccount")
	 public ResponseEntity<String> addSalaryAccount(@RequestBody SalaryAccountDto salaryAccountDto) {
	        salaryAccountService.addSalaryAccount(salaryAccountDto);
	        return new ResponseEntity<>("Salary account created successfully", HttpStatus.CREATED);
	    }

    // Endpoint to retrieve a SalaryAccount by accountNumber
    @GetMapping("salaryaccount/{accountNumber}")
    public ResponseEntity<SalaryAccount> getSalaryAccountById(@PathVariable int accountNumber) {
        SalaryAccount salaryAccount = salaryAccountService.getSalaryAccountById(accountNumber);
        return salaryAccount != null ? ResponseEntity.ok(salaryAccount) : ResponseEntity.notFound().build();
    }

    // Endpoint to retrieve all SalaryAccounts
    @GetMapping("/salaryaccount")
    public ResponseEntity<List<SalaryAccount>> getAllSalaryAccounts() {
        List<SalaryAccount> salaryAccounts = salaryAccountService.getAllSalaryAccounts();
        return ResponseEntity.ok(salaryAccounts);
    }
    
    @PostMapping("/salaryaccount/bank")
    public ResponseEntity<SalaryAccount> allocateBankToSalaryAccount(
            @RequestParam int accountNumber,
            @RequestParam int bankId) {
        SalaryAccount updatedAccount = salaryAccountService.saveAccountWithBank(accountNumber, bankId);

        if (updatedAccount != null) {
            return ResponseEntity.ok(updatedAccount);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Endpoint to update an existing SalaryAccount
    @PutMapping("salaryaccount/{accountNumber}")
    public ResponseEntity<SalaryAccount> updateSalaryAccount(
            @PathVariable int accountNumber, @RequestBody SalaryAccount salaryAccount) {
        
        // Ensure the account number in the path matches the one in the body
        if (accountNumber != salaryAccount.getAccountNumber()) {
            return ResponseEntity.badRequest().build();
        }

        SalaryAccount updatedAccount = salaryAccountService.updateSalaryAccount(salaryAccount);
        return updatedAccount != null ? ResponseEntity.ok(updatedAccount) : ResponseEntity.notFound().build();
    }
}