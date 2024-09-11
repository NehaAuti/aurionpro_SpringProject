package com.aurionpro.jpacurd.entity;

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
@Table(name="account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
	@Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="accountNumber")
    private String accountNumber;

    @Column(name="balance")
    private double balance;
}