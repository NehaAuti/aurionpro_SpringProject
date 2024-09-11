package com.aurionpro.mappings.service;

import java.util.List;

import com.aurionpro.mappings.dto.SalaryAccountDto;
import com.aurionpro.mappings.entity.SalaryAccount;
import com.aurionpro.mappings.entity.SalaryTransaction;


public interface SalaryAccountService {
    SalaryAccount addSalaryAccount(SalaryAccountDto salaryAccountDto);
    SalaryAccount getSalaryAccountById(String accountNumber);
    List<SalaryAccount> getAllSalaryAccounts();
    SalaryAccount updateSalaryAccount(String accountNumber, SalaryAccountDto salaryAccountDto);
    void deleteSalaryAccount(String accountNumber);
    SalaryAccount allocateTransactions(String accountNumber, List<SalaryTransaction> salaryTransactions);

    

}
