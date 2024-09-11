package com.aurionpro.mappings.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BankAccountRequestDto {
	
	@NotNull(message = "Customer ID is required.")
    private Integer customerId;

    @NotNull(message = "Account type is required.")
    @Size(min = 1, message = "Account type cannot be empty.")
    private String accountType;

    @NotNull(message = "Balance is required.")
  //  @Positive(message = "Balance must be positive.")
    private Double balance;
    
   // @NotNull(message = "account number is required.")
    private String accountNumber;

    private CustomerDto customerDto;

}
