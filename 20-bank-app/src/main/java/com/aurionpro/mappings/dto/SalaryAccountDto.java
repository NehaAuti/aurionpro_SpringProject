package com.aurionpro.mappings.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalaryAccountDto {
    private String accountNumber;
    private String accountHolderName;
    private Set<SalaryTransactionDto> salaryTransactions; 
}
