package com.aurionpro.mappings.service;

import java.util.List;

import com.aurionpro.mappings.dto.SalaryTransactionDto;
import com.aurionpro.mappings.entity.SalaryTransaction;

public interface SalaryTransactionService {

    // Method to add a new salary transaction
    SalaryTransaction addSalaryTransaction(SalaryTransactionDto salaryTransactionDto);

    // Method to get a salary transaction by its ID
    SalaryTransaction getSalaryTransactionById(int transactionId);

    // Method to get all salary transactions
    List<SalaryTransaction> getAllSalaryTransactions();

    // Method to update an existing salary transaction
    SalaryTransaction updateSalaryTransaction(SalaryTransaction salaryTransaction);

    // Method to delete a salary transaction by its ID
    void deleteSalaryTransaction(int transactionId);

    // Method to get all transactions for a specific salary
    SalaryTransaction saveSalaryTransactionWithSalaryId(int salaryId, SalaryTransactionDto salaryTransactionDto) ;

	
}