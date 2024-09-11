package com.aurionpro.mappings.entity;

import java.time.LocalDate;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.aurionpro.mappings.entity.Role;

@Entity
@Table(name="customer")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customerId")
    private int customerId;

    @NotBlank(message = "First name is mandatory")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name can only contain alphabetic characters")
    @Column(name = "firstName")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name can only contain alphabetic characters")
    @Column(name = "lastName")
    private String lastName;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Column(name = "emailId", unique = true)
    private String emailId;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).*$", 
    message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character")

    @Column(name = "password")
    private String password;

    @Column(name = "date_of_birth")
    @Past(message = "Date of birth must be a Past date")
    private LocalDate dateOfBirth;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "customers_roles",
        joinColumns = @JoinColumn(name = "customerId"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;


    @JsonIgnore
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BankAccount> bankAccounts;

}
