package com.aurionpro.mappings.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.mappings.entity.Student;


public interface StudentRepository extends JpaRepository<Student, Integer> {
	List<Student> findByCoursesInstructorInstructorId(int instructorId);

}
