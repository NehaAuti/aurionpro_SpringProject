package com.aurionpro.mappings.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "salaryaccount")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryAccount {

    @Id
    @Column(name = "accountNumber")
    @NotBlank(message = "Account number is required")
    @Pattern(regexp = "^[A-Z0-9]{10}$", message = "Account number must be alphanumeric and between 10 and 20 characters")
    private String accountNumber;

    @Column(name = "accountHolderName")
    @NotBlank(message = "Account holder name is required")
    @Pattern(regexp = "^[A-Za-z\\s]+$", message = "Account holder name must contain only alphabetic characters and spaces")
    private String accountHolderName;

//    @OneToOne(mappedBy = "salaryAccount")
//    private Employee employee;
}