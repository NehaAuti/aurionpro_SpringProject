package com.aurionpro.mappings.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import com.aurionpro.mappings.entity.SalaryStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryDTO {
    private int salaryId;
    private String salaryMonth;
    private Double grossSalary;
    private Double deductions;
    private Double netSalary;
    private LocalDate paymentDate;
    private SalaryStatus status;
}