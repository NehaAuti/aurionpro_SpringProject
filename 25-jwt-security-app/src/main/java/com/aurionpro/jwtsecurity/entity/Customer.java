package com.aurionpro.jwtsecurity.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="customers")
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Customer {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerId;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private long mobileNumber;
}
