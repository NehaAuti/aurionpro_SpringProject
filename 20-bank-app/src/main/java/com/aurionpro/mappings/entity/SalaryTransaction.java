package com.aurionpro.mappings.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "salary_transaction")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class SalaryTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transactionId;

	@NotNull(message = "Transaction date cannot be null")
	@PastOrPresent(message = "Transaction date must be in the past or present")
	private Date transactionDate;

	@NotNull(message = "Amount cannot be null")
	@Positive(message = "Amount must be positive")
	private double amount;

	@NotNull(message = "Status cannot be null")
	
	private String status;

	@ManyToOne
	@JoinColumn(name = "salaryId") 
	@JsonManagedReference
	private Salary salary;

	@ManyToOne
	@JoinColumn(name = "accountNumber")
	private SalaryAccount salaryAccount;
	
	

	
}
