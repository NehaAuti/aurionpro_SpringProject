package com.aurionpro.dbconnect.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="payment")
public class Payment {
	
	@Column(name="paymentId")
	@Id
  	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int paymentId;
	@Column(name="paymentDate")
	private LocalDate paymentDate;
	@Column(name="amount")
	private double amount;
	@Column(name="paymentMode")
	private String paymentMode;
	@Column(name="paymentStatus")
	private String paymentStatus;
	
	public Payment()
	{
		
	}

	public Payment(int paymentId, LocalDate paymentDate, double amount, String paymentMode, String paymentStatus) {
		super();
		this.paymentId = paymentId;
		this.paymentDate = paymentDate;
		this.amount = amount;
		this.paymentMode = paymentMode;
		this.paymentStatus = paymentStatus;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

}
