package com.aurionpro.mappings.service;


import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.mappings.dto.SalaryTransactionDto;
import com.aurionpro.mappings.entity.Salary;
import com.aurionpro.mappings.entity.SalaryAccount;
import com.aurionpro.mappings.entity.SalaryTransaction;
import com.aurionpro.mappings.repository.SalaryAccountRepository;
import com.aurionpro.mappings.repository.SalaryRepository;
import com.aurionpro.mappings.repository.SalaryTransactionRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SalaryTransactionServiceImpl implements SalaryTransactionService {

	@Autowired
    private SalaryTransactionRepository salaryTransactionRepository;
	@Autowired
    private SalaryRepository salaryRepository;
	@Autowired
    private final SalaryAccountRepository salaryAccountRepository;

    @Override
    public SalaryTransaction addSalaryTransaction(String accountNumber, SalaryTransactionDto salaryTransactionDto) {
        SalaryTransaction salaryTransaction = new SalaryTransaction();
        salaryTransaction.setTransactionDate(salaryTransactionDto.getTransactionDate());
        salaryTransaction.setAmount(salaryTransactionDto.getAmount());
        salaryTransaction.setStatus(salaryTransactionDto.getStatus());
//        
//        Salary salary = salaryRepository.findById(salaryId)
//                .orElseThrow(() -> new RuntimeException("Salary not found"));

        Optional<SalaryAccount> optionalSalaryAccount = Optional.of(salaryAccountRepository.findByAccountNumber(accountNumber));
               
        SalaryAccount salaryAccount = optionalSalaryAccount.get();

//        salaryTransaction.setSalary(salary);
        salaryTransaction.setSalaryAccount(salaryAccount);

        return salaryTransactionRepository.save(salaryTransaction);
    }

    @Override
    public SalaryTransaction updateSalaryTransaction(int transactionId, SalaryTransactionDto salaryTransactionDto) {
        Optional<SalaryTransaction> optionalSalaryTransaction = salaryTransactionRepository.findById(transactionId);
        if (optionalSalaryTransaction.isPresent()) {
            SalaryTransaction salaryTransaction = optionalSalaryTransaction.get();
            salaryTransaction.setTransactionDate(salaryTransactionDto.getTransactionDate());
            salaryTransaction.setAmount(salaryTransactionDto.getAmount());
            salaryTransaction.setStatus(salaryTransactionDto.getStatus());

            Optional<Salary> optionalSalary = salaryRepository.findById(transactionId);
            Salary salary = optionalSalary.get();

            Optional<SalaryAccount> optionalSalaryAccount = salaryAccountRepository.findById(salaryTransactionDto.getAccountNumber());
            SalaryAccount salaryAccount = optionalSalaryAccount.get();
                    
            salaryTransaction.setSalary(salary);
            salaryTransaction.setSalaryAccount(salaryAccount);

            return salaryTransactionRepository.save(salaryTransaction);
        }
        return null;
    }

    @Override
    public void deleteSalaryTransaction(int transactionId) {
        salaryTransactionRepository.deleteById(transactionId);
    }

    @Override
    public SalaryTransaction getSalaryTransactionById(int transactionId) {
        return salaryTransactionRepository.findById(transactionId).orElse(null); 
    }

    @Override
    public List<SalaryTransaction> getAllSalaryTransactions() {
        return salaryTransactionRepository.findAll();
    }
}
