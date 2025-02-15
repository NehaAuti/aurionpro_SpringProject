package com.aurionpro.mappings.service;

import java.util.List;

import com.aurionpro.mappings.dto.BankAccountDto;
import com.aurionpro.mappings.dto.PageResponse;
import com.aurionpro.mappings.entity.BankAccount;
import com.aurionpro.mappings.entity.Customer;

public interface BankAccountService {
   	    
	    BankAccountDto createBankAccount(Customer customer, String accountType, Double balance);

	    PageResponse<BankAccount> getAllBankAccounts(int pageNo, int pageSize) ;

	    BankAccount getBankAccountById(int bankId);

	    // Assuming getBankAccountByNumber should return a BankAccount, not a Customer
	    BankAccount getBankAccountByNumber(String accountNumber);

	    List<BankAccount> getBankAccountsByCustomerId(int customerId);
	    
	    int getCustomerIdByAccountNumber(String accountNumber);
	    BankAccount getBankAccountById(String accountNumber);

}
