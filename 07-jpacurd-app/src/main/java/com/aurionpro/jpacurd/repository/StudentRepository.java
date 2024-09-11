package com.aurionpro.jpacurd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.jpacurd.entity.Student;

public interface StudentRepository extends JpaRepository<Student ,Integer> {

	List<Student> findByName(String name);
}
