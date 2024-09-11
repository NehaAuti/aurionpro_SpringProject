package com.aurionpro.mappings.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bank")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Bank {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bankId;

	@NotNull(message = "Bank name cannot be null")
	private String bankName;

	@NotNull(message = "IFSC number cannot be null")
	@Pattern(regexp = "[A-Z]{4}0[A-Z0-9]{6}", message = "IFSC number must match the pattern XXXX0YYYYYY")
	private String ifscNumber;

	@OneToMany(mappedBy = "bank")
	@JsonIgnore
	private List<SalaryAccount> salaryAccounts;

}
