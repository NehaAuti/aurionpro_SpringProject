package com.aurionpro.mappings.service;

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
    private BankRepository bankRepository;

    @Autowired
    private SalaryAccountRepository salaryAccountRepository;

    @Override
    public Bank addBank(BankDto bankDto) {
        Bank bank = new Bank();
        bank.setBankName(bankDto.getBankName());
        bank.setIfscNumber(bankDto.getIfscNumber());
        return bankRepository.save(bank);
    }

    @Override
    public Bank updateBank(int bankId, BankDto bankDto) {
        Optional<Bank> optionalBank = bankRepository.findById(bankId);
        if (!optionalBank.isPresent()) {
            return null;
        }
        Bank bank = optionalBank.get();
        bank.setBankName(bankDto.getBankName());
        bank.setIfscNumber(bankDto.getIfscNumber());
        return bankRepository.save(bank);
    }

    @Override
    public void deleteBank(int bankId) {
        bankRepository.deleteById(bankId);
    }

    @Override
    public Bank getBankById(int bankId) {
        return bankRepository.findById(bankId).orElse(null);
    }

    @Override
    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }

    @Override
    public Bank allocateSalaryAccounts(int bankId, List<SalaryAccount> salaryAccounts) {
        Optional<Bank> optionalBank = bankRepository.findById(bankId);
        if (!optionalBank.isPresent()) {
            return null;
        }
        Bank bank = optionalBank.get();
        salaryAccounts.forEach(salaryAccount -> salaryAccount.setBank(bank));
        bank.setSalaryAccounts(salaryAccounts);
        return bankRepository.save(bank);
    }
}
