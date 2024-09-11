package com.aurionpro.mappings.dto;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankDto {
    private int bankId;
    private String bankName;
    private String ifscNumber;
    private List<SalaryAccountDto> salaryAccounts; 
}
