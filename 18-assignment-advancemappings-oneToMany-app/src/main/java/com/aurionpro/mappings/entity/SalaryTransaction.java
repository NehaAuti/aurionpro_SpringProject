package com.aurionpro.mappings.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="salarytransactions")
@AllArgsConstructor    // Parameterized constructor
@NoArgsConstructor     // Default constructor
@Data
public class SalaryTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="transactionId")
    private int transactionId;

    @Column(name="transactionDate")
    private LocalDate transactionDate;

    @Column(name="amount")
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "salaryId")
    @JsonBackReference
    @JsonIgnore
    private Salary salary;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountNumber") // This should match the column name in SalaryAccount
    private SalaryAccount salaryAccount;
}