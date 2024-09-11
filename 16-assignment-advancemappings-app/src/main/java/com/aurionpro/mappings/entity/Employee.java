package com.aurionpro.mappings.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employeeId")
    private int employeeId;

    @Column(name = "firstName")
    @NotBlank(message = "First name is required")
    @Pattern(regexp = "^[A-Za-z]+$", message = "First name must contain only alphabetic characters")
    private String firstName;

    @Column(name = "lastName")
    @NotBlank(message = "Last name is required")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Last name must contain only alphabetic characters")
    private String lastName;

    @Column(name = "phoneNumber")
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be 10 digits")
    private String phoneNumber;

    @Column(name = "email")
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @Column(name = "position")
    @NotBlank(message = "Position is required")
    private String position;

    @Column(name = "hireDate")
    @NotNull(message = "Hire date is required")
    private LocalDate hireDate;

    @Column(name = "salary")
    @DecimalMin(value = "0.0", inclusive = false, message = "Salary must be positive")
    private Double salary;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "accountNumber", referencedColumnName = "accountNumber")
    private SalaryAccount salaryAccount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @NotNull(message = "Status is required")
    private EmployeeStatus status;
}