package com.aurionpro.mappings.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.mappings.dto.BankDto;
import com.aurionpro.mappings.entity.Bank;
import com.aurionpro.mappings.entity.SalaryAccount;
import com.aurionpro.mappings.repository.BankRepository;
import com.aurionpro.mappings.repository.SalaryAccountRepository;

@Service
public class BankServiceImpl implements BankService {
	@Autowired
    private BankRepository bankRepo;

    @Autowired
    private SalaryAccountRepository salaryAccountRepo;

    @Override
    public Bank addBank(BankDto bankDto) {
        // Convert BankDto to Bank entity
        Bank bank = new Bank();
        bank.setBankId(bankDto.getBankId());
        bank.setBankName(bankDto.getBankName());
        bank.setIfscNo(bankDto.getIfscNo());

        // Save and return the bank entity
        return bankRepo.save(bank);
    }

    @Override
    public Bank allocateSalaryAccountsToBank(int bankId, List<SalaryAccount> salaryAccounts) {
        Optional<Bank> optionalBank = bankRepo.findById(bankId);
        if (!optionalBank.isPresent()) {
            return null;
        }

        Bank dbBank = optionalBank.get();
        List<SalaryAccount> dbSalaryAccounts = dbBank.getSalaryAccount();

        for (SalaryAccount salaryAccount : salaryAccounts) {
            SalaryAccount existingAccount = salaryAccountRepo.findById(salaryAccount.getAccountNumber())
                    .orElse(null);
            if (existingAccount != null) {
                existingAccount.setBank(dbBank);
                dbSalaryAccounts.add(existingAccount);
            }
        }

        dbBank.setSalaryAccount(dbSalaryAccounts);
        return bankRepo.save(dbBank);
    }

    @Override
    public List<Bank> getAllBanks() {
        return bankRepo.findAll();
    }

    @Override
    public Bank getBankById(int bankId) {
        return bankRepo.findById(bankId).orElse(null);
    }
}