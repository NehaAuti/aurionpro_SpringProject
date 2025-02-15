package com.aurionpro.mappings.dto;

import java.util.List;

import com.aurionpro.mappings.entity.BankAccount;
import com.aurionpro.mappings.entity.Customer;
import com.aurionpro.mappings.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionRequestDto {
	
	    @NotNull(message = "cutomerId is mandatory")
	    private int customerId;
	    @NotNull(message = "senderAccountNumber is mandatory")
	    private String senderAccountNumber;
	    
	    private String receiverAccountNumber;
	    
	    @NotNull(message = "Amount is mandatory")
	    @DecimalMin(value = "1", inclusive = true, message = "Amount must be a positive number or zero")
	    private double amount;
	    
	    @NotNull(message = "transactionType is mandatory")
	    private String transactionType;

}
