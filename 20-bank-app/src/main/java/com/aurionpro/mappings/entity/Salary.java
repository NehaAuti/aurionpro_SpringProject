package com.aurionpro.mappings.entity;


import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "salary")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Salary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int salaryId;

	@Column
	private String salaryMonth;

	@NotNull(message = "Gross salary cannot be null")
	@PositiveOrZero(message = "Gross salary must be zero or positive")
	private double grossSalary;

	@NotNull(message = "Deductions cannot be null")
	@PositiveOrZero(message = "Deductions must be zero or positive")
	private double deductions;

	
	@Column
	private Double netSalary;

	@NotNull(message = "Payment date cannot be null")
	@PastOrPresent(message = "Payment date must be in the past or present")
	private Date paymentDate;

	@Column
	private String status;



}
