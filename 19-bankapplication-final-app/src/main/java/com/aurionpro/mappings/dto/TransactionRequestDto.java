package com.aurionpro.mappings.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionRequestDto {

    @NotNull(message = "customerId is mandatory")
    private int customerId;

    @NotNull(message = "senderAccountNumber is mandatory")
    private String senderAccountNumber;

    private String receiverAccountNumber;

    @NotNull(message = "Amount is mandatory")
    @DecimalMin(value = "1", inclusive = true, message = "Amount must be a positive number")
    private double amount;

    @NotNull(message = "transactionType is mandatory")
    private String transactionType;
}