package com.aurionpro.mappings.dto;

import com.aurionpro.mappings.entity.Employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientDto {
    private int clientId;
    private String companyName;
    private String registrationNumber;
    private String contactPerson;
    private String contactEmail;
    private String contactNumber;
    private String address;
    private String status;
    private LocalDate creationDate;
    private String kycStatus;
    private List<Employee> employees;
}