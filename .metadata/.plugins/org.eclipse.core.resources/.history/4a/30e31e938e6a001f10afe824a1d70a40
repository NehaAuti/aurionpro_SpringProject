package com.aurionpro.mappings.service;

import java.util.List;

import com.aurionpro.mappings.dto.PageResponse;
import com.aurionpro.mappings.entity.BankAccount;
import com.aurionpro.mappings.entity.Transaction;


public interface TransactionService {
	
	//Transaction newTransaction(TransactionType transactionType, String senderAccNo, String receiverAccNo, Double amount);
	
	PageResponse<Transaction> getAllTransactions(int pageNo, int pageSize);
	
    public Transaction createTransaction(int customerId, String senderAccountNumber, String receiverAccountNumber, double amount, String transactionType);
	
	boolean isCustomerValid(int customerId);
	
	boolean isAccountValid(String accountNumber);
	
	public boolean hasSufficientBalance(BankAccount account, double amount);
	
	List<Transaction> getAllTransactionsByAccountNumber(String accountNumber);
	

}
