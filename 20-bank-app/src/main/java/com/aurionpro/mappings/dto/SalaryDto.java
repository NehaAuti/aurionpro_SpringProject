package com.aurionpro.mappings.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalaryDto {
    private int salaryId;
    private String salaryMonth; // Represented as a String for the Month type
    private double grossSalary;
    private double deductions;
    private Double netSalary; // Automatically calculated
    private Date paymentDate;
    private String status;
}
