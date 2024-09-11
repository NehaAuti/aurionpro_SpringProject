package com.aurionpro.mappings.entity;

import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "salary_account") 
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class SalaryAccount {

    	@Id
	    @Column(name = "account_number")
	    private String accountNumber; 

	    @NotNull(message = "Account holder name cannot be null")
	    @Column(name = "account_holder_name")
	    private String accountHolderName;
	
	   

	    @OneToMany(mappedBy = "salaryAccount", cascade = CascadeType.ALL)
	    @JsonIgnore
	    private List<Employee> employees; 

	    @OneToMany(mappedBy = "salaryAccount", cascade = CascadeType.ALL)
	    @JsonIgnore
	    private List<SalaryTransaction> salaryTransactions;
	
	    @ManyToOne
	    @JoinColumn(name = "bank_id", referencedColumnName = "bankId")
	    private Bank bank; 
}
