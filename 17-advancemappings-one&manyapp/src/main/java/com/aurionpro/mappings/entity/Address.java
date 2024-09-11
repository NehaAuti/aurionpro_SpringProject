package com.aurionpro.mappings.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="address")
@AllArgsConstructor    //parameter constructor
@NoArgsConstructor     //default constructor
@Data
public class Address {
	@Id
	@Column(name="addressId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addressId;
	@Column(name="buildingName")
	private String buildingName;
	@Column(name="city")
	private String city;
	@Column(name="pincode")
	private int pincode;

}
