package com.aurionpro.mappings.repository;

import java.time.Month;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aurionpro.mappings.entity.Salary;


@Repository
public interface SalaryRepository extends JpaRepository<Salary, Integer> {

    List<Salary> findBySalaryMonth(Month month);

}
