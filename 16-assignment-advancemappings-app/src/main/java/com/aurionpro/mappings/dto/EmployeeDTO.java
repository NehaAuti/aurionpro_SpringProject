package com.aurionpro.mappings.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import com.aurionpro.mappings.entity.EmployeeStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private int employeeId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String position;
    private LocalDate hireDate;
    private Double salary;
    private EmployeeStatus status;
}
