package com.aurionpro.mappings.entity;

import java.sql.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "passbooks")

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Passbook {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passbookId")
	private int passbookId;
	@Column(name = "senderAccountNumber")
	private String senderAccountNumber;
	@Column(name = "receiverAccountNumber")
    private String receiverAccountNumber;
	@Column(name = "amount")
    private double amount;
	@Column(name = "transactionDate")
    private Timestamp transactionDate;
	@Column(name = "transactionType")
    private String transactionType;

}
