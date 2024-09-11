package com.aurionpro.jpacurd.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.jpacurd.entity.Student;

public interface StudentRepository extends JpaRepository<Student ,Integer> {

	Page<Student>findByName(String name,Pageable pageable);
}
