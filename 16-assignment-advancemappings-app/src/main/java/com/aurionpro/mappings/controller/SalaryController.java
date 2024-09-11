package com.aurionpro.mappings.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.mappings.dto.PageResponse;
import com.aurionpro.mappings.dto.SalaryDTO;
import com.aurionpro.mappings.dto.SalaryTransactionDTO;
import com.aurionpro.mappings.entity.Salary;
import com.aurionpro.mappings.entity.SalaryTransaction;
import com.aurionpro.mappings.error.ResourceNotFoundException;
import com.aurionpro.mappings.service.SalaryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/salaryapp")
public class SalaryController {
	
	@Autowired
	private SalaryService salaryService;
	
	@GetMapping("/salary")
	public ResponseEntity<PageResponse<Salary>> getAllSalaries(
	        @RequestParam int pageno,
	        @RequestParam int pagesize) {
	    PageResponse<Salary> response = salaryService.getAllSalaries(pageno, pagesize);
	    return new ResponseEntity<>(response, HttpStatus.OK);
	}

	 
	 @PostMapping("/salary")
	    public ResponseEntity<Salary> addSalary(@Valid @RequestBody Salary salary) {
	        Salary savedSalary = salaryService.addSalary(salary);
	        return new ResponseEntity<>(savedSalary, HttpStatus.CREATED);
	    }

	    @GetMapping("salary/{salaryId}")
	    public ResponseEntity<SalaryDTO> getSalaryById(@PathVariable int salaryId) {
	        SalaryDTO salaryDTO = salaryService.getSalaryById(salaryId);
	        if (salaryDTO != null) {
	            return new ResponseEntity<>(salaryDTO, HttpStatus.OK);
	        } else {
	            throw new ResourceNotFoundException("Salary not found with ID: " + salaryId);
	        }
	    }

	    @GetMapping("salary/transaction/{salaryId}")
	    public ResponseEntity<SalaryTransactionDTO> getSalaryTransactionById(@PathVariable int salaryId) {
	        SalaryTransactionDTO transactionDTO = salaryService.getSalaryTransactionById(salaryId);
	        if (transactionDTO != null) {
	            return new ResponseEntity<>(transactionDTO, HttpStatus.OK);
	        } else {
	            throw new ResourceNotFoundException("Salary transaction not found with ID: " + salaryId);
	        }
	    }

	    @PostMapping("salary/{salaryId}")
	    public ResponseEntity<String> partiallyUpdateSalary(
	            @PathVariable int salaryId,
	            @RequestBody SalaryDTO salaryDTO) {
	        boolean updated = salaryService.partiallyUpdateSalary(salaryId, salaryDTO);
	        if (updated) {
	            return new ResponseEntity<>("Salary updated successfully", HttpStatus.OK);
	        } else {
	            throw new ResourceNotFoundException("Salary not found with ID: " + salaryId);
	        }
	    }
	}