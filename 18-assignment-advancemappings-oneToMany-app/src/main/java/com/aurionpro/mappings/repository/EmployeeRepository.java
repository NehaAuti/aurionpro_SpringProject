package com.aurionpro.mappings.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aurionpro.mappings.entity.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

} 