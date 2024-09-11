package com.aurionpro.mappings.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.mappings.entity.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {

}
