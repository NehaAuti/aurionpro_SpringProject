package com.aurionpro.mappings.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.mappings.dto.SalaryAccountDto;
import com.aurionpro.mappings.entity.SalaryAccount;
import com.aurionpro.mappings.entity.SalaryTransaction;
import com.aurionpro.mappings.repository.SalaryAccountRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SalaryAccountServiceImpl implements SalaryAccountService {

    @Autowired
    private SalaryAccountRepository salaryAccountRepository;

    @Override
    public SalaryAccount addSalaryAccount(SalaryAccountDto salaryAccountDto) {
        SalaryAccount salaryAccount = new SalaryAccount();
        salaryAccount.setAccountNumber(salaryAccountDto.getAccountNumber());
        salaryAccount.setAccountHolderName(salaryAccountDto.getAccountHolderName());
        return salaryAccountRepository.save(salaryAccount);
    }

    @Override
    public SalaryAccount getSalaryAccountById(String accountNumber) {
        return salaryAccountRepository.findById(accountNumber).orElseThrow(() -> new RuntimeException("Salary account not found"));
    }

    @Override
    public List<SalaryAccount> getAllSalaryAccounts() {
        return salaryAccountRepository.findAll();
    }

    @Override
    public SalaryAccount updateSalaryAccount(String accountNumber, SalaryAccountDto salaryAccountDto) {
        SalaryAccount salaryAccount = getSalaryAccountById(accountNumber);
        salaryAccount.setAccountHolderName(salaryAccountDto.getAccountHolderName());
        return salaryAccountRepository.save(salaryAccount);
    }

    @Override
    public void deleteSalaryAccount(String accountNumber) {
        salaryAccountRepository.deleteById(accountNumber);
    }

    @Override
    public SalaryAccount allocateTransactions(String accountNumber, List<SalaryTransaction> salaryTransactions) {
        SalaryAccount salaryAccount = getSalaryAccountById(accountNumber);
        salaryTransactions.forEach(salaryTransaction -> salaryTransaction.setSalaryAccount(salaryAccount));
        salaryAccount.setSalaryTransactions(salaryTransactions);
        return salaryAccountRepository.save(salaryAccount);
    }
    
}
