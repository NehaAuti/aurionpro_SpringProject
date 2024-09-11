package com.aurionpro.dbconnect.service;

import java.util.List;

import com.aurionpro.dbconnect.entity.Customer;

public interface CustomerService {
	
	List<Customer> getAllCustomer();
    Customer getCustomer(int customerId);
    void addCustomer(Customer customer);
    List <Customer> getCustomerByName(String firstName,String lastName);
    void deleteCustomer(int customerId);
    
}
