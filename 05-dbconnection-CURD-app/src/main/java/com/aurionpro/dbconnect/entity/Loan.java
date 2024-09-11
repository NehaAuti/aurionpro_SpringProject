package com.aurionpro.dbconnect.entity;


import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="loan")
public class Loan {
	
	    @Column(name="loanId")
	    @Id
     	@GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int loanId;            // Unique identifier for the loan
	    @Column(name="loanAmount")
	    private Double loanAmount;     // The amount of the loan
	    @Column(name="interestRate")
	    private Double interestRate;   // The interest rate of the loan
	    @Column(name="loanterm")
	    private Integer loanTerm;      // The term of the loan in months
	    @Column(name="startDate")
	    private LocalDate startDate;   // The start date of the loan
	    @Column(name="endDate")
	    private LocalDate endDate;     // The end date of the loan
	    @Column(name="loanStatus")
	    private String loanStatus;     // The status of the loan (e.g., "Active", "Closed")
	    
	    public Loan()
	    {
	    	
	    }

		public Loan(int loanId, Double loanAmount, Double interestRate, Integer loanTerm, LocalDate startDate,
				LocalDate endDate, String loanStatus) {
			super();
			this.loanId = loanId;
			this.loanAmount = loanAmount;
			this.interestRate = interestRate;
			this.loanTerm = loanTerm;
			this.startDate = startDate;
			this.endDate = endDate;
			this.loanStatus = loanStatus;
		}

		public int getLoanId() {
			return loanId;
		}

		public void setLoanId(int loanId) {
			this.loanId = loanId;
		}

		public Double getLoanAmount() {
			return loanAmount;
		}

		public void setLoanAmount(Double loanAmount) {
			this.loanAmount = loanAmount;
		}

		public Double getInterestRate() {
			return interestRate;
		}

		public void setInterestRate(Double interestRate) {
			this.interestRate = interestRate;
		}

		public Integer getLoanTerm() {
			return loanTerm;
		}

		public void setLoanTerm(Integer loanTerm) {
			this.loanTerm = loanTerm;
		}

		public LocalDate getStartDate() {
			return startDate;
		}

		public void setStartDate(LocalDate startDate) {
			this.startDate = startDate;
		}

		public LocalDate getEndDate() {
			return endDate;
		}

		public void setEndDate(LocalDate endDate) {
			this.endDate = endDate;
		}

		public String getLoanStatus() {
			return loanStatus;
		}

		public void setLoanStatus(String loanStatus) {
			this.loanStatus = loanStatus;
		}

}


