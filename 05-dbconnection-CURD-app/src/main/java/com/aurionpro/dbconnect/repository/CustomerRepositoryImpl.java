package com.aurionpro.dbconnect.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aurionpro.dbconnect.entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
@Repository
public class CustomerRepositoryImpl implements CustomerRepository{
	
	@Autowired
	private EntityManager manager;

	@Override
	public List<Customer> getAllCustomer() {
		// TODO Auto-generated method stub
		TypedQuery<Customer> query = manager.createQuery("select c from Customer c",Customer.class);
		return query.getResultList();
		
	}

	@Override
	public Customer getCustomer(int customerId) {
		// TODO Auto-generated method stub
		return manager.find(Customer.class, customerId);
	}

	@Override
	@Transactional
	public void addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		manager.merge(customer);
		
	}

	@Override
	public List<Customer> getCustomerByName(String firstName, String lastName) {
	    TypedQuery<Customer> query = manager.createQuery(
	        "SELECT c FROM Customer c WHERE c.firstName = :firstName AND c.lastName = :lastName",
	        Customer.class
	    );
	    query.setParameter("firstName", firstName);
	    query.setParameter("lastName", lastName);
	    return query.getResultList();
	}

	@Transactional
    public void deleteCustomer(int customerId) {
        Customer customer = manager.find(Customer.class, customerId);
        if (customer != null) {
            manager.remove(customer);
        }
    }

}
