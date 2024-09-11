package com.aurionpro.jwtsecurity.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aurionpro.jwtsecurity.entity.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
