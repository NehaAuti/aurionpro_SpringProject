package com.aurionpro.mappings.dto;

import java.time.LocalDate;

import com.aurionpro.mappings.entity.SalaryAccount;
import com.aurionpro.mappings.entity.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryTransactionDto {
    private int transactionId;
    private LocalDate transactionDate;
    private Double amount;
    private Status status;
    private int salaryId; // To map with Salary
    private SalaryAccount salaryAccount; // To map with SalaryAccount
}
