package com.aurionpro.jpacurd.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="client")

@AllArgsConstructor    //parameter constructor
@NoArgsConstructor     //default constructor
@Getter                //getters
@Setter                //setters
public class Client {
	@Column(name="companyId")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int clientId;
	@Column(name="companyName")
    private String companyName;
	@Column(name="registrationNumber")
    private String registrationNumber;
	@Column(name="contactPerson")
    private String contactPerson;
	@Column(name="contactEmail")
    private String contactEmail;
	@Column(name="contactNumber")
    private String contactNumber;
	@Column(name="address")
    private String address;
	@Column(name="status")
    private String status;
	@Column(name="creationDate")
    private LocalDate creationDate;
    @Column(name="kycStatus")
    private String kycStatus;
	

}

