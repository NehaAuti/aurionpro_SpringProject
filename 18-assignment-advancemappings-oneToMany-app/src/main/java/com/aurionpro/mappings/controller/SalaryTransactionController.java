package com.aurionpro.mappings.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aurionpro.mappings.dto.SalaryTransactionDto;
import com.aurionpro.mappings.entity.SalaryTransaction;
import com.aurionpro.mappings.service.SalaryTransactionService;

@RestController
@RequestMapping("/bankapp")
public class SalaryTransactionController {

    @Autowired
    private SalaryTransactionService salaryTransactionService;

 // Create a new SalaryTransaction
    @PostMapping("/salarytransaction")

    public ResponseEntity<SalaryTransaction> addSalaryTransaction(@RequestBody SalaryTransactionDto dto) {
        SalaryTransaction transaction = salaryTransactionService.addSalaryTransaction(dto);
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }

    // Get a SalaryTransaction by ID
    @GetMapping("salarytransaction/{id}")
    public ResponseEntity<SalaryTransaction> getSalaryTransactionById(@PathVariable int transactionId) {
        SalaryTransaction transaction = salaryTransactionService.getSalaryTransactionById(transactionId);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    // Get all SalaryTransactions
    @GetMapping("/salarytransaction")
    public ResponseEntity<List<SalaryTransaction>> getAllSalaryTransactions() {
        List<SalaryTransaction> transactions = salaryTransactionService.getAllSalaryTransactions();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    // Update a SalaryTransaction
    @PutMapping("salarytransaction/{id}")
    public ResponseEntity<SalaryTransaction> updateSalaryTransaction(@PathVariable int transactionId,
                                                                      @RequestBody SalaryTransactionDto dto) {
        // Retrieve the existing transaction
        SalaryTransaction existingTransaction = salaryTransactionService.getSalaryTransactionById(transactionId);
        // Set the new values
        existingTransaction.setAmount(dto.getAmount());
        existingTransaction.setStatus(dto.getStatus());
        existingTransaction.setTransactionDate(dto.getTransactionDate());
        // Update the SalaryAccount as needed
        // Ensure you handle this according to your DTO and service logic
        // e.g., existingTransaction.setSalaryAccount(someValue);

        SalaryTransaction updatedTransaction = salaryTransactionService.updateSalaryTransaction(existingTransaction);
        return new ResponseEntity<>(updatedTransaction, HttpStatus.OK);
    }

    // Delete a SalaryTransaction
    @DeleteMapping("salarytransaction/{id}")
    public ResponseEntity<Void> deleteSalaryTransaction(@PathVariable int transactionId) {
        salaryTransactionService.deleteSalaryTransaction(transactionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Save SalaryTransaction with SalaryId
    @PostMapping("salarytransaction/save")
    public ResponseEntity<SalaryTransaction> allocateSalaryTransactionWithSalaryId(@RequestParam int salaryId,
                                                                                  @RequestBody SalaryTransactionDto dto) {
        SalaryTransaction transaction = salaryTransactionService.saveSalaryTransactionWithSalaryId(salaryId, dto);
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }
}