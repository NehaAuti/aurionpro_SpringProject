package com.aurionpro.mappings.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.mappings.dto.SalaryAccountDto;
import com.aurionpro.mappings.entity.Bank;
import com.aurionpro.mappings.entity.SalaryAccount;
import com.aurionpro.mappings.repository.BankRepository;
import com.aurionpro.mappings.repository.SalaryAccountRepository;

@Service
public class SalaryAccountServiceImpl implements SalaryAccountService {

	@Autowired
    private SalaryAccountRepository salaryAccountRepo;
	
	@Autowired
    private BankRepository bankRepo;

	public SalaryAccount addSalaryAccount(SalaryAccountDto salaryAccountDto) {
        
        SalaryAccount salaryAccount = new SalaryAccount();
        salaryAccount.setAccountNumber(salaryAccountDto.getAccountNumber());
        salaryAccount.setAccountHolderName(salaryAccountDto.getAccountHolderName());
        return salaryAccountRepo.save(salaryAccount);
    }
    @Override
    public SalaryAccount getSalaryAccountById(int accountNumber) {
        return salaryAccountRepo.findById(accountNumber).orElse(null);
    }

    @Override
    public List<SalaryAccount> getAllSalaryAccounts() {
        return salaryAccountRepo.findAll();
    }
    
    public SalaryAccount saveAccountWithBank(int accountNumber, int bankId) {
        // Find the SavingAccount by accountNumber
        Optional<SalaryAccount> accountOpt = salaryAccountRepo.findByAccountNumber(accountNumber);
        
        // Find the Bank by bankId
        Optional<Bank> bankOpt = bankRepo.findById(bankId);

        // Check if both the SavingAccount and Bank are present
        if (accountOpt.isPresent() && bankOpt.isPresent()) {
            SalaryAccount account = accountOpt.get();
            Bank bank = bankOpt.get();

            // Set the bank for the SavingAccount
            account.setBank(bank);

            // Save and return the updated SavingAccount
            return salaryAccountRepo.save(account);
        }

        // If SavingAccount or Bank is not found, return null
        return null;
    }


    @Override
    public SalaryAccount updateSalaryAccount(SalaryAccount salaryAccount) {
        if (salaryAccountRepo.existsById(salaryAccount.getAccountNumber())) {
            return salaryAccountRepo.save(salaryAccount);
        }
        return null;
    }

}
