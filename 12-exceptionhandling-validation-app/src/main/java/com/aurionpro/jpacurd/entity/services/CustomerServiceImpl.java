package com.aurionpro.jpacurd.entity.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.aurionpro.jpacurd.dto.PageResponseCustomer;
import com.aurionpro.jpacurd.entity.Customer;
import com.aurionpro.jpacurd.exception.CustomerNotFoundException;
import com.aurionpro.jpacurd.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepo;
    
    @Override
    public PageResponseCustomer<Customer> getAllCustomers(int pageno, int pagesize) {
        Pageable pageable = PageRequest.of(pageno, pagesize);
        Page<Customer> customerPage = customerRepo.findAll(pageable);
        
        PageResponseCustomer<Customer> customerPageResponse = new PageResponseCustomer<>();
        customerPageResponse.setTotalPages(customerPage.getTotalPages());
        customerPageResponse.setSize(customerPage.getSize());
        customerPageResponse.setTotalElements(customerPage.getTotalElements());
        customerPageResponse.setLastPage(customerPage.isLast());
        customerPageResponse.setContent(customerPage.getContent());
        return customerPageResponse;
    }

    @Override
    public void addCustomer(Customer customer) {
        customerRepo.save(customer);
    }

    @Override
    public Customer getCustomerById(int customerId) {
        Optional<Customer> dbCustomer = customerRepo.findById(customerId);
        if (!dbCustomer.isPresent()) {
            throw new CustomerNotFoundException();
        }
        return dbCustomer.get();
    }

    @Override
    public void deleteCustomer(int customerId) {
    	Optional<Customer> dbCustomer = customerRepo.findById(customerId);
        if (!dbCustomer.isPresent()) {
            throw new CustomerNotFoundException();
            
        }
        customerRepo.deleteById(customerId);
    	
    }

    @Override
    public PageResponseCustomer<Customer> getAllCustomersByFirstName(String firstName, int pageno, int pagesize) {
        Pageable pageable = PageRequest.of(pageno, pagesize);
        Page<Customer> customerPage = customerRepo.findByFirstName(firstName, pageable);
        
        PageResponseCustomer<Customer> customerPageResponse = new PageResponseCustomer<>();
        customerPageResponse.setTotalPages(customerPage.getTotalPages());
        customerPageResponse.setSize(customerPage.getSize());
        customerPageResponse.setTotalElements(customerPage.getTotalElements());
        customerPageResponse.setLastPage(customerPage.isLast());
        customerPageResponse.setContent(customerPage.getContent());
        return customerPageResponse;
    }


}