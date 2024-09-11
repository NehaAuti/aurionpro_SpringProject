package com.aurionpro.mappings.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.mappings.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

	Student findByRollnumber(int rollnumber);
	

	
}
