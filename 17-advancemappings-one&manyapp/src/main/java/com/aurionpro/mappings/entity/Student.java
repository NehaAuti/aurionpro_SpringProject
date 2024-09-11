package com.aurionpro.mappings.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="students")
@AllArgsConstructor    //parameter constructor
@NoArgsConstructor     //default constructor
@Data
public class Student {
	
	@Id
	@Column(name="rollnumber")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rollnumber;
	@Column(name="name")
	private String name;
	@Column(name="age")
	private int age;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="addressId")
	private Address address;
	
	

}
