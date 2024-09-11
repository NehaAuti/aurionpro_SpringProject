package com.aurionpro.mappings.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	
	@Enumerated(EnumType.STRING)
	@Column(name="status")
	private Status status;

	@Column(name="creationDate")
    private LocalDate creationDate;
    @Column(name="kycStatus")
    private String kycStatus;
    
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Employee> employees;

}

