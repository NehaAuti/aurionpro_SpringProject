package com.aurionpro.jwtsecurity.services;

import java.util.List;

import com.aurionpro.jwtsecurity.entity.Customer;

public interface CustomerService {

	List<Customer> getAllCustomers();
	Customer getCustomerById(int customerId);
	
}
