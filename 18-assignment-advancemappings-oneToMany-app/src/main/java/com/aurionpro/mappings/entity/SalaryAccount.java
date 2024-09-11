package com.aurionpro.mappings.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="salaryaccount")
@AllArgsConstructor    // Parameterized constructor
@NoArgsConstructor     // Default constructor
@Data
public class SalaryAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="accountNumber")
    private int accountNumber;

    @Column(name="accountHolderName")
    private String accountHolderName;

    @OneToMany(mappedBy = "salaryAccount", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<SalaryTransaction> salaryTransactions;

    
    @OneToMany(mappedBy = "salaryAccount", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Employee> employees; // Use `employees` instead of `salaryTransactions` if it reflects the correct relationship
    

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bankId")
    @JsonBackReference
    private Bank bank;

    
    
}