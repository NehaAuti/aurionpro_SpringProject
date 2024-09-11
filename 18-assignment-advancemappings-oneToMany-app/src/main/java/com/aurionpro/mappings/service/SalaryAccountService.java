package com.aurionpro.mappings.service;

import java.util.List;

import com.aurionpro.mappings.dto.SalaryAccountDto;
import com.aurionpro.mappings.entity.SalaryAccount;


public interface SalaryAccountService {
	
	SalaryAccount addSalaryAccount(SalaryAccountDto salaryAccountDto);
    
	SalaryAccount getSalaryAccountById(int accountNumber);

    List<SalaryAccount> getAllSalaryAccounts();
    
    SalaryAccount saveAccountWithBank(int accountNumber, int bankId) ;

    SalaryAccount updateSalaryAccount(SalaryAccount salaryAccount);

	    

}
