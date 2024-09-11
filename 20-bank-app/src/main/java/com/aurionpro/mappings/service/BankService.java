package com.aurionpro.mappings.service;



import java.util.List;

import com.aurionpro.mappings.dto.BankDto;
import com.aurionpro.mappings.entity.Bank;
import com.aurionpro.mappings.entity.SalaryAccount;

public interface BankService {
    Bank addBank(BankDto bankDto);
    Bank updateBank(int bankId, BankDto bankDto);
    void deleteBank(int bankId);
    Bank getBankById(int bankId);
    List<Bank> getAllBanks();
    
    Bank allocateSalaryAccounts(int bankId, List<SalaryAccount> salaryAccounts);
}
