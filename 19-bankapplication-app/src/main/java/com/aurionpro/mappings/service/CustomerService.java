package com.aurionpro.mappings.service;

import java.util.List;

import com.aurionpro.mappings.dto.PageResponse;
import com.aurionpro.mappings.entity.Customer;

public interface CustomerService {

    Customer addCustomer(Customer customer);

    PageResponse<Customer> getAllCustomers(int pageNo, int pageSize);

    Customer getCustomerById(int customerId);

    Customer findByCustomerId(Integer customerId);

    // This method can be used for editing customer details with validation
    Customer editCustomer(int customerId, String currentPassword, String newPassword, String newFirstName, String newLastName);

}
