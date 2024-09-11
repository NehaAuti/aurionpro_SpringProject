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
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "loan")
@AllArgsConstructor    // Parameterized constructor
@NoArgsConstructor     // Default constructor
@Getter                // Getters
@Setter                // Setters
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loanId")
    private int loanId;            

    @NotNull(message = "Loan amount cannot be null")
    @PositiveOrZero(message = "Loan amount must be zero or positive")
    @Column(name = "loanAmount")
    private Double loanAmount;   

    @NotNull(message = "Interest rate cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Interest rate must be greater than 0")
    @Column(name = "interestRate")
    private Double interestRate;   

    @NotNull(message = "Loan term cannot be null")
    @Min(value = 1, message = "Loan term must be at least 1 month")
    @Positive(message = "Loan term must be positive")
    @Column(name = "loanTerm")
    private Integer loanTerm;      

    @NotNull(message = "Start date cannot be null")
    @PastOrPresent(message = "Start date cannot be in the future")
    @Column(name = "startDate")
    private LocalDate startDate;   

    @NotNull(message = "End date cannot be null")
    @PastOrPresent(message = "End date cannot be in the future")
    @Column(name = "endDate")
    private LocalDate endDate;     
    
    @NotNull(message = "Loan status cannot be null")
    @Enumerated(EnumType.STRING)
    @Column(name = "loanStatus")
    private LoanStatus loanStatus; 
}