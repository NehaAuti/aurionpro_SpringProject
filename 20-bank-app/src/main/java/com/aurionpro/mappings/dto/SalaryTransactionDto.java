package com.aurionpro.mappings.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalaryTransactionDto {
    private int transactionId;
    private Date transactionDate;
    private double amount;
    private String status;
    private int salaryId; // Reference to Salary entity
    private String accountNumber; // Reference to SalaryAccount entity
}
