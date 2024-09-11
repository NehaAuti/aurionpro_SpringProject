package com.aurionpro.dbconnect.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.dbconnect.entity.Loan;
import com.aurionpro.dbconnect.service.LoanService;



@RestController
@RequestMapping("/loanapp")
public class LoanController {
	
	@Autowired
	private LoanService loanService;
	
	@GetMapping("/loan")
	public ResponseEntity<List<Loan>> getAllLoan()
	{
		return ResponseEntity.ok(loanService.getAllLoan());
	}
	
	@GetMapping("/loan/{loanId}")
	public ResponseEntity<Loan> getLoan(@PathVariable int loanId)
	{
		return ResponseEntity.ok(loanService.getLoan(loanId));
	}
	
	
	@PostMapping("/loan")
	public String addLoan(@RequestBody Loan loan)
	{
		loanService.addLoan(loan);
		return "Record added successfully"; 
	}
	
	@DeleteMapping("/loan/{loanId}")
    public ResponseEntity<String> deleteLoan(@PathVariable int loanId) {
        loanService.deleteLoan(loanId);
        return ResponseEntity.ok("Loan deleted successfully");
    }
	

}
