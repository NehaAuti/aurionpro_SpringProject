package com.aurionpro.mappings.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.mappings.entity.Course;


public interface CourseRepository extends JpaRepository<Course , Integer>{

}
