package com.aurionpro.mappings.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
@Entity
@Table(name="bankaccount")
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class BankAccount {


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="bankId")
    private int bankId;

    @NotBlank(message = "Account number is mandatory")
    @Pattern(regexp = "^[A-Za-z0-9]{10}$", message = "Account number must be 10 alphanumeric characters")
    @Column(name="accountNumber", unique = true)
    private String accountNumber;

    @NotBlank(message = "Account type is mandatory")
    @Size(max = 50, message = "Account type cannot exceed 50 characters")
    @Column(name="accountType")
    private String accountType;

    @Column(name = "balance")
   // @DecimalMin(value = "5000.00", message = "Balance must be at least 5000")
    private double balance;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    @JsonIgnore // Ignore the customer field during serialization
    @JsonManagedReference // Manage this reference
    private Customer customer;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "customer_id", nullable = false)
//    private Customer customer;

    @NotNull(message = "Status is mandatory")
    @Enumerated(EnumType.STRING)
    private BankStatus status;

    @JsonIgnore
    @OneToMany(mappedBy = "senderAccount", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Transaction> sentTransactions;

    @JsonIgnore
    @OneToMany(mappedBy = "receiverAccount", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Transaction> receivedTransactions;

    
 // Getters and Setters
    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "bankId=" + bankId +
                ", accountNumber='" + accountNumber + '\'' +
                ", accountType='" + accountType + '\'' +
                ", balance=" + balance +
                '}';
    }
    
}