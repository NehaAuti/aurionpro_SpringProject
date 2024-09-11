package com.aurionpro.mappings.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="banks")
@AllArgsConstructor    // Parameterized constructor
@NoArgsConstructor     // Default constructor
@Data
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="bankId")
    private int bankId;

    @Column(name="bankName")
    private String bankName;

    @Column(name="ifscNo")
    private String ifscNo;


    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<SalaryAccount> salaryAccount;

}