package com.aurionpro.mappings.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aurionpro.mappings.entity.BankAccount;
import com.aurionpro.mappings.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
	
	List<Transaction> findAll();	  
	//List<Transaction> findByAccountNumberAndCustomerId(String accountNumber, String customerId);
	//List<Transaction> findByAccountCustomerId(String senderAccountNumber, String receiverAccountNumber);
	
    List<Transaction> findBySenderAccountOrReceiverAccount(BankAccount senderAccountNumber, BankAccount receiverAccountNumber);

    List<Transaction> findBySenderAccountAccountNumber(String accountNumber);
    List<Transaction> findByReceiverAccountAccountNumber(String accountNumber);
}