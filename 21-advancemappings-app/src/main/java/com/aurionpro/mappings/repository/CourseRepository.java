package com.aurionpro.mappings.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.mappings.entity.Course;


public interface CourseRepository extends JpaRepository<Course, Integer> {
	List<Course> findByInstructorInstructorId(int instructorId);

}
