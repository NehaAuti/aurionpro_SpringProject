package com.aurionpro.mappings.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aurionpro.mappings.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

   
    Optional<Employee> findByEmail(String email);

}
