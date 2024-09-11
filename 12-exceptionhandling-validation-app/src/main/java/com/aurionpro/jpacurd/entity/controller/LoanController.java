package com.aurionpro.jpacurd.entity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aurionpro.jpacurd.dto.PageResponseLoan;
import com.aurionpro.jpacurd.entity.Loan;
import com.aurionpro.jpacurd.entity.LoanStatus;
import com.aurionpro.jpacurd.entity.services.LoanService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/loanapp")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping("/loan")
    public ResponseEntity<PageResponseLoan<Loan>> getAllLoans(
        @RequestParam int pageno, 
        @RequestParam int pagesize) 
    {
        return ResponseEntity.ok(loanService.getAllLoans(pageno, pagesize));
    }

    @GetMapping("/loan/{id}")
    public ResponseEntity<Loan> getLoanById(@PathVariable int id) {
        return ResponseEntity.ok(loanService.getByLoanId(id));
    }

    @PostMapping("/loan")
    public ResponseEntity<String> addLoan(@Valid @RequestBody Loan loan) {
        // Log or print the received loan object to verify its content
        System.out.println("Received Loan: " + loan);
        
        if (loan.getLoanAmount() == null) {
            throw new IllegalArgumentException("Loan amount cannot be null");
        }
        
        // Additional validations and logic
        loanService.addLoan(loan);
        return ResponseEntity.ok("Loan added successfully");
    }


    @DeleteMapping("/loan/{id}")
    public ResponseEntity<String> deleteLoan(@PathVariable int id) {
        loanService.deleteLoan(id);
        return ResponseEntity.ok("Loan deleted successfully");
    }

    @GetMapping("/loans")
    public ResponseEntity<PageResponseLoan<Loan>> getAllLoansByStatus(
        @RequestParam LoanStatus loanStatus, 
        @RequestParam int pageno, 
        @RequestParam int pagesize) 
    {
        return ResponseEntity.ok(loanService.getAllLoansByLoanStatus(loanStatus, pageno, pagesize));
    }
}