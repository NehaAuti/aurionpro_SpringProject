package com.aurionpro.mappings.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "transactions")
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private int transactionId;

    @NotNull(message = "Amount is mandatory")
    @DecimalMin(value = "1", inclusive = true, message = "Amount must be a positive number or zero")
    @Column(name = "amount")
    private double amount;
    
	@Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private  TransactionType transactionType;
	
	@Column(name = "transactionDate")
    private LocalDate transactionDate;
	
    @ManyToOne
    @JoinColumn(name = "sender_account_number", referencedColumnName = "accountNumber")
    private BankAccount senderAccount;

    @ManyToOne
    @JoinColumn(name = "receiver_account_number", referencedColumnName = "accountNumber", nullable = true)
    private BankAccount receiverAccount;

}
