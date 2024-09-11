package com.aurionpro.mappings.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employee")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeId;

	@NotNull(message = "First name cannot be null")
	@Pattern(regexp = "^[A-Za-z]+$", message = "First name must contain only letters")
	@Column(name = "first_name")
	private String firstName;

	@NotNull(message = "Last name cannot be null")
	@Pattern(regexp = "^[A-Za-z]+$", message = "Last name must contain only letters")
	@Column(name = "last_name")
	private String lastName;

	@NotNull(message = "Phone number cannot be null")
	@Positive(message = "Phone number must be positive")

	@Column(name = "phone_number")
	private long phoneNumber;

	@NotNull(message = "Email cannot be null")
	@Email(message = "Email should be valid")
	@Column(name = "email")
	private String email;

	@NotNull(message = "Position cannot be null")

	@Column(name = "position")
	private String position;

	@NotNull(message = "Hire date cannot be null")
	@PastOrPresent(message = "Hire date must be in the past or present")
	@Column(name = "hire_date")
	private Date hireDate;

	@PositiveOrZero(message = "Salary must be zero or positive")
	@Column(name = "salary")
	private double salary;

	@ManyToOne
	@JoinColumn(name = "account_number", referencedColumnName = "account_number")
	private SalaryAccount salaryAccount;

	@ManyToOne
	@JoinColumn(name = "client_id", referencedColumnName = "clientId")
	private Client client;

	@NotNull(message = "Status cannot be null")

	@Column(name = "status")
	private String status;

}
