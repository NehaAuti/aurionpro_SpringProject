package com.aurionpro.mappings.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.mappings.dto.SalaryTransactionDto;
import com.aurionpro.mappings.entity.Salary;
import com.aurionpro.mappings.entity.SalaryAccount;
import com.aurionpro.mappings.entity.SalaryTransaction;
import com.aurionpro.mappings.repository.SalaryAccountRepository;
import com.aurionpro.mappings.repository.SalaryRepository;
import com.aurionpro.mappings.repository.SalaryTransactionRepository;
import com.aurionpro.mappings.service.SalaryTransactionService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SalaryTransactionServiceImpl implements SalaryTransactionService {

    @Autowired
    private SalaryTransactionRepository salaryTransactionRepository;

    @Autowired
    private SalaryRepository salaryRepository;

    @Autowired
    private SalaryAccountRepository salaryAccountRepository;

    @Override
    public SalaryTransaction addSalaryTransaction(SalaryTransactionDto dto) {
        SalaryTransaction transaction = new SalaryTransaction();
        transaction.setAmount(dto.getAmount());
        transaction.setStatus(dto.getStatus());
        transaction.setTransactionDate(dto.getTransactionDate());
        
        // Set the Salary using salaryId
        Optional<Salary> salary = salaryRepository.findById(dto.getSalaryId());
        if (salary.isPresent()) {
            transaction.setSalary(salary.get());
        } else {
            throw new EntityNotFoundException("Salary not found");
        }

        // Set the SalaryAccount using account number
        Optional<SalaryAccount> account = salaryAccountRepository.findByAccountNumber(dto.getSalaryAccount().getAccountNumber());
        if (account.isPresent()) {
            transaction.setSalaryAccount(account.get());
        } else {
            throw new EntityNotFoundException("Salary account not found");
        }

        return salaryTransactionRepository.save(transaction);
    }



    @Override
    public SalaryTransaction getSalaryTransactionById(int transactionId) {
        return salaryTransactionRepository.findById(transactionId)
                                          .orElseThrow(() -> new RuntimeException("Transaction not found"));
    }

    @Override
    public List<SalaryTransaction> getAllSalaryTransactions() {
        return salaryTransactionRepository.findAll();
    }

    @Override
    public SalaryTransaction updateSalaryTransaction(SalaryTransaction salaryTransaction) {
        return salaryTransactionRepository.save(salaryTransaction);
    }

    @Override
    public void deleteSalaryTransaction(int transactionId) {
        salaryTransactionRepository.deleteById(transactionId);
    }

    @Override
    public SalaryTransaction saveSalaryTransactionWithSalaryId(int salaryId, SalaryTransactionDto salaryTransactionDto) {
        // Find the Salary entity by its ID
        Optional<Salary> optionalSalary = salaryRepository.findById(salaryId);
        if (!optionalSalary.isPresent()) {
            throw new RuntimeException("Salary not found");
        }

        Salary salary = optionalSalary.get();
        // Create a new SalaryTransaction entity and set its properties
        SalaryTransaction salaryTransaction = new SalaryTransaction();
        salaryTransaction.setTransactionDate(salaryTransactionDto.getTransactionDate());
        salaryTransaction.setAmount(salaryTransactionDto.getAmount());
        salaryTransaction.setStatus(salaryTransactionDto.getStatus());
        salaryTransaction.setSalary(salary); // Set the Salary entity

        // Assuming dto.getSalaryAccount() returns an Optional<SalaryAccount>
        Optional<SalaryAccount> optionalSalaryAccount = Optional.of(salaryTransactionDto.getSalaryAccount());
        if (optionalSalaryAccount.isPresent()) {
            salaryTransaction.setSalaryAccount(optionalSalaryAccount.get());
        } else {
            throw new EntityNotFoundException("Salary account not found");
        }

        // Save and return the SalaryTransaction entity
        return salaryTransactionRepository.save(salaryTransaction);
    }

}
