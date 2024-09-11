package com.aurionpro.mappings.service;


import java.util.List;

import com.aurionpro.mappings.dto.SalaryTransactionDto;
import com.aurionpro.mappings.entity.SalaryTransaction;

public interface SalaryTransactionService {
    SalaryTransaction addSalaryTransaction(String accountNumber, SalaryTransactionDto salaryTransactionDto);
    SalaryTransaction updateSalaryTransaction(int transactionId, SalaryTransactionDto salaryTransactionDto);
    void deleteSalaryTransaction(int transactionId);
    SalaryTransaction getSalaryTransactionById(int transactionId);
    List<SalaryTransaction> getAllSalaryTransactions();
}
