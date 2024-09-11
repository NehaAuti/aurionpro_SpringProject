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
@Table(name="employee")

@AllArgsConstructor    //parameter constructor
@NoArgsConstructor     //default constructor
@Getter                //getters
@Setter                //setters

public class Employee {
	@Column(name="employeeId")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int employeeId;
	@Column(name="firstName")
    private String firstName;
	@Column(name="lastName")
    private String lastName;
	@Column(name="phoneNumber")
    private String phoneNumber;
	@Column(name="email")
    private String email;
	@Column(name="position")
    private String position;
	@Column(name="hireDate")
    private LocalDate hireDate;
	@Column(name="salary")
    private double salary;
	@Column(name="bankAccountNumber")
    private String bankAccountNumber;
	@Column(name="bankIfscNumber")
    private String bankIfscNumber;
	@Column(name="status")
    private String status;


}
