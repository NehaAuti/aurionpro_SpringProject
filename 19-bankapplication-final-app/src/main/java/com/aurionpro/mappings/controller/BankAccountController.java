package com.aurionpro.mappings.controller;

import com.aurionpro.mappings.dto.BankAccountDto;
import com.aurionpro.mappings.dto.BankAccountRequestDto;
import com.aurionpro.mappings.dto.PageResponse;
import com.aurionpro.mappings.entity.BankAccount;
import com.aurionpro.mappings.entity.Customer;
import com.aurionpro.mappings.exception.BankAccountNotFoundException;
import com.aurionpro.mappings.exception.CustomerNotFoundException;
import com.aurionpro.mappings.exception.InvalidInputException;
import com.aurionpro.mappings.service.BankAccountService;
import com.aurionpro.mappings.service.CustomerService;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bankapp")
public class BankAccountController {

	 private static final Logger logger = LoggerFactory.getLogger(BankAccountController.class);

	    @Autowired
	    private BankAccountService bankAccountService;
	    
	    @Autowired
	    private CustomerService customerService;

	    @PostMapping("/createBankAccount")
	    @PreAuthorize("hasRole('ADMIN')")
	    public ResponseEntity<BankAccountDto> createBankAccount(
	            @Valid @RequestBody BankAccountRequestDto bankAccountRequestDto) {
	        logger.info("Request received to create a bank account for customer ID: {}", bankAccountRequestDto.getCustomerId());

	        try {
	            Integer customerId = bankAccountRequestDto.getCustomerId();
	            if (customerId == null && bankAccountRequestDto.getCustomerDto() != null) {
	                customerId = bankAccountRequestDto.getCustomerDto().getCustomerId();
	            }

	            if (customerId == null) {
	                throw new InvalidInputException("Customer ID must not be null");
	            }

	            Customer customer = customerService.findByCustomerId(customerId);
	            BankAccountDto bankAccountDto = bankAccountService.createBankAccount(
	                    customer,
	                    bankAccountRequestDto.getAccountType(),
	                    bankAccountRequestDto.getBalance()
	            );
	            logger.info("Bank account created successfully for customer ID: {}", customerId);
	            return new ResponseEntity<>(bankAccountDto, HttpStatus.CREATED);
	        } catch (CustomerNotFoundException e) {
	            logger.error("Customer not found for ID: {}", bankAccountRequestDto.getCustomerId(), e);
	            throw e;
	        } catch (InvalidInputException e) {
	            logger.error("Invalid input: {}", e.getMessage(), e);
	            throw e;
	        }
	    }

	    @GetMapping("/customerId/{accountNumber}")
	    @PreAuthorize("hasRole('ADMIN')")
	    public ResponseEntity<Map<String, Integer>> getCustomerIdByAccountNumber(@Valid @PathVariable String accountNumber) {
	        logger.info("Request received to get customer ID for account number: {}", accountNumber);
	        try {
	            int customerId = bankAccountService.getCustomerIdByAccountNumber(accountNumber);
	            Map<String, Integer> response = new HashMap<>();
	            response.put("customerId", customerId);
	            return new ResponseEntity<>(response, HttpStatus.OK);
	        } catch (RuntimeException e) {
	            logger.error("Failed to find customer ID for account number: {}", accountNumber, e);
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

	    @GetMapping("/bankaccount")
	    @PreAuthorize("hasRole('ADMIN')")
	    public ResponseEntity<PageResponse<BankAccount>> getAllBankAccounts(@Valid
	            @RequestParam int pageNo,
	            @RequestParam int pageSize) {
	        logger.info("Request received to get all bank accounts with page number: {}, page size: {}", pageNo, pageSize);
	        PageResponse<BankAccount> pageResponse = bankAccountService.getAllBankAccounts(pageNo, pageSize);
	        return new ResponseEntity<>(pageResponse, HttpStatus.OK);
	    }

	    @GetMapping("/bankaccount/{bankId}")
	    @PreAuthorize("hasRole('ADMIN')")
	    public ResponseEntity<BankAccount> getBankAccountById(@Valid @PathVariable int bankId) {
	        logger.info("Request received to get bank account by ID: {}", bankId);
	        try {
	            BankAccount bankAccount = bankAccountService.getBankAccountById(bankId);
	            return new ResponseEntity<>(bankAccount, HttpStatus.OK);
	        } catch (BankAccountNotFoundException e) {
	            logger.error("Bank account not found for ID: {}", bankId, e);
	            throw e;
	        }
	    }

	    @GetMapping("/accountnumber/{accountNumber}")
	    @PreAuthorize("hasRole('ADMIN')")
	    public ResponseEntity<BankAccount> getBankAccountByNumber(@Valid @PathVariable String accountNumber) {
	        logger.info("Request received to get bank account by account number: {}", accountNumber);
	        try {
	            BankAccount bankAccount = bankAccountService.getBankAccountByNumber(accountNumber);
	            return new ResponseEntity<>(bankAccount, HttpStatus.OK);
	        } catch (BankAccountNotFoundException e) {
	            logger.error("Bank account not found for account number: {}", accountNumber, e);
	            throw e;
	        } catch (InvalidInputException e) {
	            logger.error("Invalid input for account number: {}", accountNumber, e);
	            throw e;
	        }
	    }

	    @GetMapping("/customer/{customerId}")
	    @PreAuthorize("hasRole('ADMIN')")
	    public ResponseEntity<List<BankAccount>> getBankAccountsByCustomerId(@Valid @PathVariable int customerId) {
	        logger.info("Request received to get bank accounts for customer ID: {}", customerId);
	        List<BankAccount> accounts = bankAccountService.getBankAccountsByCustomerId(customerId);
	        return ResponseEntity.ok(accounts);
	    }
	}

    
