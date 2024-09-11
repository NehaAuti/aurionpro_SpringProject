package com.aurionpro.mappings.service;

import java.util.List;

import com.aurionpro.mappings.dto.BankDto;
import com.aurionpro.mappings.entity.Bank;
import com.aurionpro.mappings.entity.SalaryAccount;

public interface BankService {

	    Bank addBank(BankDto bankDto);
	    Bank allocateSalaryAccountsToBank(int bankId, List<SalaryAccount> salaryAccounts);
	    List<Bank> getAllBanks();
	    Bank getBankById(int bankId);
}
