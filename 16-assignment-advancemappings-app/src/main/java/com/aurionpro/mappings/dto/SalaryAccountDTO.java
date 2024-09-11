package com.aurionpro.mappings.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryAccountDTO {
    private String accountNumber;
    private String accountHolderName;
}