package com.aurionpro.mappings.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.time.LocalDate;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.mappings.dto.PageResponse;
import com.aurionpro.mappings.entity.BankAccount;
import com.aurionpro.mappings.entity.Customer;
import com.aurionpro.mappings.entity.Passbook;
import com.aurionpro.mappings.entity.Transaction;
import com.aurionpro.mappings.entity.TransactionType;
import com.aurionpro.mappings.exception.CustomerNotFoundException;
import com.aurionpro.mappings.exception.InsufficientBalanceException;
import com.aurionpro.mappings.exception.InvalidAmountException;
import com.aurionpro.mappings.exception.InvalidTransactionTypeException;
import com.aurionpro.mappings.repository.BankAccountRepository;
import com.aurionpro.mappings.repository.CustomerRepository;
import com.aurionpro.mappings.repository.PassbookRepository;
import com.aurionpro.mappings.repository.TransactionRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TransactionServiceImpl implements TransactionService {
    
    private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

    @Autowired
    private TransactionRepository transactionRepo;

    @Autowired
    private BankAccountRepository bankAccountRepo;

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private PassbookRepository passbookRepo;
    
    @Autowired
    private EmailService emailService;
    
    private static final double MINIMUM_BALANCE = 5000.00;

    @Transactional
    public Transaction createTransaction(int customerId, String senderAccountNumber, String receiverAccountNumber, double amount, String transactionType) {

        logger.info("Creating transaction. Customer ID: {}, Sender Account: {}, Receiver Account: {}, Amount: {}, Transaction Type: {}",
                customerId, senderAccountNumber, receiverAccountNumber, amount, transactionType);

        // Validate transaction type
        TransactionType transactionEnum;
        try {
            transactionEnum = TransactionType.valueOf(transactionType.toUpperCase());
        } catch (IllegalArgumentException e) {
            logger.error("Invalid transaction type: {}", transactionType);
            throw new InvalidTransactionTypeException("Invalid transaction type: " + transactionType);
        }

        // Validate amount
        if (amount <= 0) {
            logger.error("Invalid amount: {}", amount);
            throw new InvalidAmountException("Transaction amount must be greater than zero.");
        }

        // Fetch and validate the sender account
        Optional<BankAccount> optionalSenderAccount = bankAccountRepo.findByAccountNumber(senderAccountNumber);
        BankAccount senderAccount = optionalSenderAccount.orElseThrow(() -> {
            logger.error("Sender account number not found: {}", senderAccountNumber);
            return new IllegalArgumentException("Sender account number not found.");
        });

        // Log sender's current balance for debugging
        logger.info("Sender's current balance: {}", senderAccount.getBalance());
        logger.info("Transaction amount: {}", amount);

        // Handle different transaction types
        switch (transactionEnum) {
        case TRANSFER:
            logger.info("Processing transfer transaction.");

            // Validate receiver account number
            if (receiverAccountNumber == null || receiverAccountNumber.trim().isEmpty()) {
                logger.error("Receiver account number must be provided for a transfer.");
                throw new IllegalArgumentException("Receiver account number must be provided for a transfer.");
            }

            if (senderAccountNumber.equals(receiverAccountNumber)) {
                logger.error("Sender and receiver account numbers cannot be the same for a transfer.");
                throw new IllegalArgumentException("Sender and receiver account numbers cannot be the same.");
            }

            // Fetch and validate receiver account
            BankAccount receiverAccount = bankAccountRepo.findByAccountNumber(receiverAccountNumber)
                    .orElseThrow(() -> {
                        logger.error("Receiver account number not found: {}", receiverAccountNumber);
                        return new IllegalArgumentException("Receiver account number not found.");
                    });

            // Validate sender's balance for transfer, ensuring minimum balance of 5000 is maintained
            if (senderAccount.getBalance() - amount < 5000) {
                logger.error("Insufficient balance for transfer. Minimum balance of 5000 must be maintained.");
                throw new IllegalArgumentException("Insufficient balance for transfer. Minimum balance of 5000 must be maintained.");
            }

            // Perform the transfer
            senderAccount.setBalance(senderAccount.getBalance() - amount);
            receiverAccount.setBalance(receiverAccount.getBalance() + amount);

            // Save updated accounts
            bankAccountRepo.save(senderAccount);
            bankAccountRepo.save(receiverAccount);

            logger.info("Transfer successful from {} to {} for amount {}", senderAccountNumber, receiverAccountNumber, amount);
            break;

        case DEBIT:
            logger.info("Processing debit transaction.");

            // Validate sender's balance for debit
            if (senderAccount.getBalance() - amount < 5000) {
                logger.error("Insufficient balance for debit. Minimum balance of 5000 must be maintained.");
                throw new IllegalArgumentException("Insufficient balance for debit. Minimum balance of 5000 must be maintained.");
            }

            // Debit the amount
            senderAccount.setBalance(senderAccount.getBalance() - amount);
            bankAccountRepo.save(senderAccount);

            logger.info("Debit successful from {} for amount {}", senderAccountNumber, amount);
            break;

        case CREDIT:
            logger.info("Processing credit transaction.");

            // Credit the amount
            senderAccount.setBalance(senderAccount.getBalance() + amount);
            bankAccountRepo.save(senderAccount);

            logger.info("Credit successful to {} for amount {}", senderAccountNumber, amount);
            break;

        default:
            logger.error("Invalid transaction type: {}", transactionEnum);
            throw new InvalidTransactionTypeException("Invalid transaction type.");
        }

        // Create and save the transaction record
        Transaction transaction = new Transaction();
        transaction.setSenderAccount(senderAccount);
        transaction.setReceiverAccount(transactionEnum == TransactionType.TRANSFER ? bankAccountRepo.findByAccountNumber(receiverAccountNumber).orElse(null) : null);
        transaction.setAmount(amount);
        transaction.setTransactionType(transactionEnum);
        transaction.setTransactionDate(LocalDate.now());

        Transaction savedTransaction = transactionRepo.save(transaction);
        logger.info("Transaction created successfully with ID: {}", savedTransaction.getTransactionId());

        // Create and save the passbook record
        Passbook passbook = new Passbook();
        passbook.setSenderAccountNumber(senderAccountNumber);
        passbook.setReceiverAccountNumber(receiverAccountNumber);
        passbook.setAmount(amount);
        passbook.setTransactionDate(Timestamp.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        passbook.setTransactionType(transactionType);

        passbookRepo.save(passbook);
        logger.info("Passbook entry created successfully for transaction ID: {}", savedTransaction.getTransactionId());

        // Send confirmation email
        String subject = "Transaction Confirmation";
        String body = String.format("Dear Customer,\n\nYour transaction with ID %d has been successfully processed.\nAmount: %.2f\nType: %s\nDate: %s\n\nThank you,\nBank",
                savedTransaction.getTransactionId(), amount, transactionType, LocalDate.now());
        emailService.sendEmail(senderAccount.getCustomer().getEmailId(), subject, body);

        return savedTransaction;
    }

    @Override
    public boolean isCustomerValid(int customerId) {
        boolean isValid = customerRepo.existsById(customerId);
        logger.debug("Checking if customer is valid: Customer ID: {}, Valid: {}", customerId, isValid);
        return isValid;
    }

    @Override
    public boolean isAccountValid(String accountNumber) {
        boolean isValid = bankAccountRepo.existsByAccountNumber(accountNumber);
        logger.debug("Checking if account is valid: Account Number: {}, Valid: {}", accountNumber, isValid);
        return isValid;
    }

    public void processTransaction(Transaction transaction) {
        try {
            // Process the transaction
            bankAccountRepo.save(transaction.getSenderAccount());
            bankAccountRepo.save(transaction.getReceiverAccount());
        } catch (ConstraintViolationException e) {
            // Handle the exception
            throw new IllegalArgumentException("Transaction failed due to constraint violation: " + e.getMessage());
        }
    }
    
    public void validateBalance(BankAccount bankaccount, double balance) {
        if (bankaccount.getBalance() - balance < 5000) {
            throw new IllegalArgumentException("Insufficient balance. Minimum balance of 5000 must be maintained.");
        }
    }


    // Method to fetch the customer email by ID
    private String getCustomerEmailById(int customerId) {
        return customerRepo.findById(customerId)
                .map(Customer::getEmailId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }


    @Override
    public PageResponse<Transaction> getAllTransactions(int pageNo, int pageSize) {
        logger.info("Retrieving all transactions with pagination - PageNo: {}, PageSize: {}", pageNo, pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Transaction> transactionsPage = transactionRepo.findAll(pageable);

        boolean isLastPage = transactionsPage.isLast();

        PageResponse<Transaction> pageResponse = new PageResponse<>(
            transactionsPage.getContent(),
            transactionsPage.getNumber(),
            transactionsPage.getSize(),
            transactionsPage.getTotalElements(),
            transactionsPage.getTotalPages(),
            isLastPage
        );

        logger.info("Retrieved {} transactions on page {}", pageResponse.getContent().size(), pageNo);
        return pageResponse;
    }


    
    @Override
    public List<Transaction> getAllTransactionsByAccountNumber(String accountNumber) {
        logger.info("Fetching transactions for account number: {}", accountNumber);

        BankAccount bankAccount = bankAccountRepo.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found for account number: " + accountNumber));
        
        // Log bank account details for debugging
        logger.debug("Found BankAccount: {}", bankAccount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.addAll(bankAccount.getSentTransactions());
        transactions.addAll(bankAccount.getReceivedTransactions());

        logger.debug("Sent Transactions: {}", bankAccount.getSentTransactions());
        logger.debug("Received Transactions: {}", bankAccount.getReceivedTransactions());

        logger.info("Retrieved {} transactions for account number: {}", transactions.size(), accountNumber);
        return transactions;
    }


	@Override
	public boolean isValidTransactionType(String transactionType) {
		// TODO Auto-generated method stub
		return false;
	}
}
