package com.aurionpro.mappings.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="employee")

@AllArgsConstructor    //parameter constructor
@NoArgsConstructor     //default constructor
@Getter                //getters
@Setter                //setters

public class Employee {
	@Column(name="employeeId")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int employeeId;
	@Column(name="firstName")
    private String firstName;
	@Column(name="lastName")
    private String lastName;
	@Column(name="phoneNumber")
    private String phoneNumber;
	@Column(name="email")
    private String email;
	@Column(name="position")
    private String position;
	@Column(name="hireDate")
    private LocalDate hireDate;
	@Column(name="salary")
    private double salary;
	
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "accountNumber") // Matches the column name in SalaryAccount
	    @JsonBackReference // Prevents recursive JSON serialization
	    private SalaryAccount salaryAccount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clientId")
    @JsonBackReference
    private Client client;




}
