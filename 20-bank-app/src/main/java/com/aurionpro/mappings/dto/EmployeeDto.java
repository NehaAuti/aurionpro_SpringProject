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
public class EmployeeDto {
    private int employeeId;
    private String firstName;
    private String lastName;
    private long phoneNumber;
    private String email;
    private String position;
    private Date hireDate;
    private double salary;
    private String salaryAccountNumber;
    private String status;

}
