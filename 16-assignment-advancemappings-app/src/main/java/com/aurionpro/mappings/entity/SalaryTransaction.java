package com.aurionpro.mappings.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "salarytransaction")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transactionId")
    private int transactionId;

    @Column(name = "transactionDate")
    @NotNull(message = "Transaction date is required")
    private LocalDate transactionDate;

    @Column(name = "amount")
    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @NotNull(message = "Status is required")
    private TransactionStatus status;
}