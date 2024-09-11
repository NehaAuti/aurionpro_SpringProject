package com.aurionpro.jpacurd.entitys;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="customer")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customerId")
    private int customerId;
    
    @NotBlank(message="First name cannot be blank")
    @Size(max=50, message="First name must not exceed 50 characters")
    @Column(name="firstName")
    private String firstName;
    
    @NotBlank(message="Last name cannot be blank")
    @Size(max=50, message="Last name must not exceed 50 characters")
    @Column(name="lastName")
    private String lastName;
    
    @NotNull(message="Date of Birth cannot be null")
    @Column(name="dateOfBirth")
    private LocalDate dateOfBirth;
    
    @NotBlank(message="Email ID cannot be blank")
    @Email(message="Email ID should be valid")
    @Column(name="emailId")
    private String emailId;
    
    @NotBlank(message="Mobile number cannot be blank")
    @Size(min=10, max=15, message="Mobile number should be between 10 and 15 characters")
    @Column(name="mobileNumber")
    private String mobileNumber;
}