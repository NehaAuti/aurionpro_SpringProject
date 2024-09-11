package com.aurionpro.mappings.dto;

import java.util.Date;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {
    private int clientId;
    private String companyName;
    private int registrationNumber;
    private String contactPerson;
    private long contactNumber;
    private String address;
    private Date creationDate;
    private String kycStatus;
    private List<EmployeeDto> employees; 

}
