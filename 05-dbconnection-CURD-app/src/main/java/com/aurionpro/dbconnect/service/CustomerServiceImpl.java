package com.aurionpro.dbconnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.dbconnect.entity.Customer;
import com.aurionpro.dbconnect.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepository customerRepo;

	@Override
	public List<Customer> getAllCustomer() {
		// TODO Auto-generated method stub
		return customerRepo.getAllCustomer();
	}

	@Override
	public Customer getCustomer(int customerId) {
		// TODO Auto-generated method stub
		return customerRepo.getCustomer(customerId);
	}

	@Override
	public void addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customerRepo.addCustomer(customer);
		
	}

	@Override
	public List<Customer> getCustomerByName(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return customerRepo.getCustomerByName(firstName, lastName);
	}

	@Override
	public void deleteCustomer(int customerId) {
		// TODO Auto-generated method stub
		customerRepo.deleteCustomer(customerId);
		
	}

}
