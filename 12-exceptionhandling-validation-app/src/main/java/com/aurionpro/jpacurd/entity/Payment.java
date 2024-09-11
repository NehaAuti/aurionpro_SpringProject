package com.aurionpro.jpacurd.entity;

import java.time.LocalDate;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "payment")
@AllArgsConstructor    // Parameterized constructor
@NoArgsConstructor     // Default constructor
@Getter                // Getters
@Setter   
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="paymentId")
    private int paymentId;
    
    @Column(name="paymentDate")
    @NotNull(message = "Payment date cannot be null")
    @PastOrPresent(message = "Payment date cannot be in the future")
    private LocalDate paymentDate;
    
    @Column(name="amount")
    @NotNull(message = "Amount cannot be null")
    @Positive(message = "Amount must be positive")
    private double amount;
    
    @Enumerated(EnumType.STRING)
    @Column(name="paymentMode")
    @NotNull(message = "Payment mode cannot be null")
    private PaymentMode paymentMode;
    
    @Enumerated(EnumType.STRING)
    @Column(name="paymentStatus")
    @NotNull(message = "Payment status cannot be null")
    private PaymentStatus paymentStatus;
}