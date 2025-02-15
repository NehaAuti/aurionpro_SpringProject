package com.aurionpro.mappings.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aurionpro.mappings.dto.PageResponse;
import com.aurionpro.mappings.dto.TransactionDTO;
import com.aurionpro.mappings.entity.BankAccount;
import com.aurionpro.mappings.entity.Transaction;
import com.aurionpro.mappings.exception.InvalidInputException;
import com.aurionpro.mappings.service.BankAccountService;
import com.aurionpro.mappings.service.TransactionService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bankapp")
public class TransactionController {

    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private BankAccountService bankAccountService;
    
    @PostMapping("/newtransaction")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<String> createTransaction(@Valid @RequestBody TransactionDTO transactionRequest) {

        // Validate transaction type
        if (!isValidTransactionType(transactionRequest.getTransactionType())) {
            logger.error("Invalid transaction type: {}", transactionRequest.getTransactionType());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid transaction type.");
        }

        // Validate amount
        if (transactionRequest.getAmount() <= 0) {
            logger.error("Invalid transaction amount: {}", transactionRequest.getAmount());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Amount must be positive.");
        }

        // Log transaction details
        logTransactionDetails(
                transactionRequest.getSenderAccountNumber(),
                transactionRequest.getReceiverAccountNumber(),
                transactionRequest.getAmount(),
                transactionRequest.getTransactionType()
        );

        try {
            // Create the transaction
            logger.info("Creating transaction for customer ID: {}", transactionRequest.getCustomerId());
            Transaction transaction = transactionService.createTransaction(
                    transactionRequest.getCustomerId(),
                    transactionRequest.getSenderAccountNumber(),
                    transactionRequest.getReceiverAccountNumber(),
                    transactionRequest.getAmount(),
                    transactionRequest.getTransactionType()
            );
            logger.info("Transaction created successfully with ID: {}", transaction.getTransactionId());
            return ResponseEntity.status(HttpStatus.OK).body("Transaction successful!! Transaction ID: " + transaction.getTransactionId());

        } catch (IllegalArgumentException e) {
            logger.error("Error during transaction creation: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error occurred during transaction creation: {}", e.getMessage(), e);
            throw new InvalidInputException("Unexpected error occurred during transaction creation.");
        }
    }

    @GetMapping("/transactions/{accountNumber}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Transaction>> getAllTransactionsByAccountNumber(@Valid @PathVariable String accountNumber) {
        logger.info("Fetching transactions for account number: {}", accountNumber);
        try {
            List<Transaction> transactions = transactionService.getAllTransactionsByAccountNumber(accountNumber);
            if (transactions.isEmpty()) {
                logger.warn("No transactions found for account number: {}", accountNumber);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            logger.info("Transactions fetched successfully for account number: {}", accountNumber);
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error occurred while retrieving transactions: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/transactions")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PageResponse<Transaction>> viewTransactions(@Valid @RequestParam int pageNo, @RequestParam int pageSize) {
        logger.info("Fetching transactions with pagination: pageNo = {}, pageSize = {}", pageNo, pageSize);
        try {
            PageResponse<Transaction> pageResponse = transactionService.getAllTransactions(pageNo, pageSize);
            logger.info("Transactions fetched successfully, total records: {}", pageResponse.getTotalPages());
            return new ResponseEntity<>(pageResponse, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error occurred while fetching transactions: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private boolean isValidTransactionType(String transactionType) {
        return "transfer".equalsIgnoreCase(transactionType) ||
               "debit".equalsIgnoreCase(transactionType) ||
               "credit".equalsIgnoreCase(transactionType);
    }

    private void logTransactionDetails(String senderAccountNumber, String receiverAccountNumber, double amount, String transactionType) {
        logger.info("Transaction details - Sender Account Number: {}, Receiver Account Number: {}, Amount: {}, Transaction Type: {}",
                senderAccountNumber, receiverAccountNumber, amount, transactionType);
    }
}

    
   
//@GetMapping("/bankaccount/{accountNumber}")
//@PreAuthorize("hasRole('ADMIN')")
//public ResponseEntity<BankAccount> getBankAccountById(@PathVariable String accountNumber) {
//  try {
//      BankAccount bankAccount = bankAccountService.getBankAccountById(accountNumber);
//      return new ResponseEntity<>(bankAccount, HttpStatus.OK);
//  } catch (RuntimeException e) {
//      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//  }
//}


//    @GetMapping("transaction/{transactionId}")
//    public ResponseEntity<Transaction> getTransactionById(@RequestParam int transactionId) {
//        try {
//            Transaction transaction = transactionService.getTransactionById(transactionId);
//            return new ResponseEntity<>(transaction, HttpStatus.OK);
//        } catch (TransactionNotFoundException e) {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//    }
