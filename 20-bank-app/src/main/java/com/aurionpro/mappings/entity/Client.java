package com.aurionpro.mappings.entity;

import java.util.Date;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "client") 
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clientId; 

    @NotNull(message = "Company name cannot be null")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Company name must contain only characters")
    private String companyName;

    @NotNull(message = "Registration number cannot be null")
    @Positive(message = "Registration number must be positive")
    private int registrationNumber;

    @NotNull(message = "Contact person cannot be null")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Contact person name must contain only characters")
    private String contactPerson;

    @NotNull(message = "Contact number cannot be null")
    @Positive(message = "Contact number must be positive")
  
    private long contactNumber;

    @NotNull(message = "Address cannot be null")
    private String address;

    @NotNull(message = "Creation date cannot be null")
    @PastOrPresent(message = "Creation date must be in the past or present")
    @Column(name = "creation_date")
    private Date creationDate;

    @NotNull(message = "KYC status cannot be null")
    @Column(name="kyc_status")
    private String kycStatus;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Employee> employees; 

   
}
