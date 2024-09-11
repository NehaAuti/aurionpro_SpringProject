package com.aurionpro.mappings.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import com.aurionpro.mappings.entity.TransactionStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryTransactionDTO {
    private int transactionId;
    private LocalDate transactionDate;
    private Double amount;
    private TransactionStatus status;
}