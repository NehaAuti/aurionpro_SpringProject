package com.aurionpro.jpacurd.entity.services;

import com.aurionpro.jpacurd.dto.PageResponseCustomer;

import com.aurionpro.jpacurd.entitys.Customer;
public interface CustomerService {

    PageResponseCustomer<Customer> getAllCustomers(int pageno, int pagesize);

    void addCustomer(Customer customer);

    Customer getCustomerById(int customerId);

    void deleteCustomer(int customerId);

    PageResponseCustomer<Customer> getAllCustomersByFirstName(String firstName, int pageno, int pagesize);

	
}