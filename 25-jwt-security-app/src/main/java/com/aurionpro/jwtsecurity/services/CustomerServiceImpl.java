package com.aurionpro.jwtsecurity.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.jwtsecurity.entity.Customer;
import com.aurionpro.jwtsecurity.respository.CustomerRepository;
@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepo;
	
	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return customerRepo.findAll();
	}

	@Override
	public Customer getCustomerById(int customerId) {
		// TODO Auto-generated method stub
		return customerRepo.findById(customerId).orElseThrow(()-> new NullPointerException("Customer Not Found"));
	}

}
