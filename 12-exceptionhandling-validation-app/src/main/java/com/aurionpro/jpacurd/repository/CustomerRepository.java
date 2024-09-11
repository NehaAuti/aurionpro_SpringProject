package com.aurionpro.jpacurd.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.jpacurd.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	Page<Customer> findByFirstName(String firstName, Pageable pageable);

}
