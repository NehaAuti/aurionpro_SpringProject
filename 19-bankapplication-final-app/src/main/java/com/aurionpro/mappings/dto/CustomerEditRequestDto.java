package com.aurionpro.mappings.dto;

import java.util.List;

import com.aurionpro.mappings.entity.BankAccount;
import com.aurionpro.mappings.entity.Customer;
import com.aurionpro.mappings.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerEditRequestDto {
	
	
    @NotNull(message = "Customer ID is required.")
    private Integer customerId;

    @NotBlank(message = "Current password is required.")
    private String currentPassword;

    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).*$", message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character")
    private String newPassword;

    @NotBlank(message = "First name is mandatory")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name can only contain alphabetic characters")
    private String newFirstName;

    @NotBlank(message = "Last name is mandatory")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name can only contain alphabetic characters")
    private String newLastName;

}
