package com.aurionpro.jpacurd.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="students")

@AllArgsConstructor    //parameter constructor
@NoArgsConstructor     //default constructor
@Getter                //getters
@Setter                //setters
public class Student {
	@Column(name="rollnumber")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int rollnumber;
	
	@Column(name="name")
	
	private String name;
	
	@Column(name="age")
	
	private int age;
	
}
