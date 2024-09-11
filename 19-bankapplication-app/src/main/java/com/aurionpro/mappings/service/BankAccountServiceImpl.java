package com.aurionpro.mappings.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.aurionpro.mappings.dto.BankAccountDto;
import com.aurionpro.mappings.dto.CustomerDto;
import com.aurionpro.mappings.dto.PageResponse;
import com.aurionpro.mappings.entity.AccountNumberGenerator;
import com.aurionpro.mappings.entity.BankAccount;
import com.aurionpro.mappings.entity.BankStatus;
import com.aurionpro.mappings.entity.Customer;
import com.aurionpro.mappings.exception.BankAccountNotFoundException;
import com.aurionpro.mappings.repository.BankAccountRepository;
import com.aurionpro.mappings.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BankAccountServiceImpl implements BankAccountService{

    @Autowired
    private BankAccountRepository bankAccountRepository;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    private static final Logger logger = LoggerFactory.getLogger(BankAccountServiceImpl.class);


    @Override
    public BankAccountDto createBankAccount(Customer customer, String accountType, Double balance) {
        logger.info("Creating bank account for customer ID: {}, accountType: {}, balance: {}", customer.getCustomerId(), accountType, balance);

        try {
            BankAccount bankAccount = new BankAccount();
            bankAccount.setAccountNumber(AccountNumberGenerator.generateAccountNumber());
            bankAccount.setAccountType(accountType);
            bankAccount.setBalance(balance);
            bankAccount.setCustomer(customer);
            bankAccount.setStatus(BankStatus.ACTIVE);

            bankAccountRepository.save(bankAccount);

            CustomerDto customerDto = new CustomerDto();
            customerDto.setCustomerId(customer.getCustomerId());
            customerDto.setFirstName(customer.getFirstName());
            customerDto.setLastName(customer.getLastName());
            customerDto.setEmailId(customer.getEmailId());
            customerDto.setPassword(customer.getPassword());
            customerDto.setDateOfBirth(customer.getDateOfBirth());

            BankAccountDto response = new BankAccountDto();
            response.setAccountNumber(bankAccount.getAccountNumber());
            response.setAccountType(bankAccount.getAccountType());
            response.setBalance(bankAccount.getBalance());
            response.setCustomerDto(customerDto);

            logger.info("Bank account created successfully with accountNumber: {}", bankAccount.getAccountNumber());
            return response;
        } catch (Exception e) {
            logger.error("Error occurred while creating bank account for customer ID: {}", customer.getCustomerId(), e);
            throw new RuntimeException("Failed to create bank account", e);
        }
    }

    @Override
    public PageResponse<BankAccount> getAllBankAccounts(int pageNo, int pageSize) {
        logger.info("Retrieving all bank accounts with pagination - PageNo: {}, PageSize: {}", pageNo, pageSize);
        try {
            Pageable pageable = PageRequest.of(pageNo, pageSize);
            Page<BankAccount> bankAccountsPage = bankAccountRepository.findAll(pageable);

            boolean isLastPage = bankAccountsPage.isLast();

            PageResponse<BankAccount> pageResponse = new PageResponse<>(
                bankAccountsPage.getContent(),
                bankAccountsPage.getNumber(),
                bankAccountsPage.getSize(),
                bankAccountsPage.getTotalElements(),
                bankAccountsPage.getTotalPages(),
                isLastPage
            );

            logger.info("Retrieved {} bank accounts on page {}", pageResponse.getContent().size(), pageNo);
            return pageResponse;
        } catch (Exception e) {
            logger.error("Error occurred while retrieving bank accounts with pagination", e);
            throw new RuntimeException("Failed to retrieve bank accounts", e);
        }
    }

    @Override
    public BankAccount getBankAccountById(int bankId) {
        logger.info("Retrieving bank account with ID: {}", bankId);
        return bankAccountRepository.findById(bankId)
            .orElseThrow(() -> {
                logger.error("Bank Account with ID {} not found", bankId);
                return new BankAccountNotFoundException("Bank Account with ID " + bankId + " not found");
            });
    }

    @Override
    public BankAccount getBankAccountByNumber(String accountNumber) {
        logger.info("Finding bank account with accountNumber: {}", accountNumber);

        // Sanitize the input to remove leading/trailing spaces
        String sanitizedAccountNumber = accountNumber.trim();

        return bankAccountRepository.findByAccountNumber(sanitizedAccountNumber)
            .orElseThrow(() -> {
                logger.error("Bank account not found with account number: {}", sanitizedAccountNumber);
                return new BankAccountNotFoundException("Bank account not found with account number: " + sanitizedAccountNumber);
            });
    }

    @Override
    public List<BankAccount> getBankAccountsByCustomerId(int customerId) {
        logger.info("Fetching bank accounts for customer ID: {}", customerId);

        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> {
                logger.error("Customer not found for ID: {}", customerId);
                return new IllegalArgumentException("Customer not found.");
            });

        List<BankAccount> bankAccounts = bankAccountRepository.findByCustomer(customer);
        logger.info("Retrieved {} bank accounts for customer ID: {}", bankAccounts.size(), customerId);

        return bankAccounts;
    }

    @Override
    public int getCustomerIdByAccountNumber(String accountNumber) {
        logger.info("Fetching customer ID for account number: {}", accountNumber);

        return bankAccountRepository.findByAccountNumber(accountNumber)
            .map(bankAccount -> {
                Customer customer = bankAccount.getCustomer();
                if (customer != null) {
                    int customerId = customer.getCustomerId();
                    logger.info("Found customer ID: {} for account number: {}", customerId, accountNumber);
                    return customerId;
                } else {
                    logger.error("No customer associated with account number: {}", accountNumber);
                    throw new RuntimeException("Customer not associated with account number: " + accountNumber);
                }
            })
            .orElseThrow(() -> {
                logger.error("Account number not found: {}", accountNumber);
                return new RuntimeException("Account number not found: " + accountNumber);
            });
    }

    @Override
    public BankAccount getBankAccountById(String accountNumber) {
        logger.info("Retrieving bank account by account number: {}", accountNumber);
        return bankAccountRepository.findByAccountNumber(accountNumber)
            .orElseThrow(() -> {
                logger.error("Bank account not found with account number: {}", accountNumber);
                return new BankAccountNotFoundException("Bank account not found with account number: " + accountNumber);
            });
    }
}


//    @Override
//    public void deleteBankAccount(int bankId) {
//        logger.info("Deleting bank account with ID: {}", bankId);
//        if (bankAccountRepository.existsById(bankId)) {
//            bankAccountRepository.deleteById(bankId);
//            logger.info("Bank account with ID {} deleted successfully", bankId);
//        } else {
//            logger.error("Bank Account with ID {} not found", bankId);
//            throw new BankAccountNotFoundException("Bank Account with ID " + bankId + " not found");
//        }
//    }

   



    


