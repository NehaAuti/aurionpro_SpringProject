package com.aurionpro.mappings.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.mappings.entity.Salary;
import com.aurionpro.mappings.repository.SalaryRepository;

@Service
public class SalaryServiceImpl implements SalaryService {

    @Autowired
    private SalaryRepository salaryRepo;

    @Override
    public Salary addSalary(Salary salary) {
        // Calculate net salary before saving
        salary.calculateNetSalary();
        
        // Save the salary entity to the database
        return salaryRepo.save(salary);
    }

    @Override
    public Salary getSalaryById(int salaryId) {
        return salaryRepo.findById(salaryId).orElse(null);
    }

    @Override
    public List<Salary> getAllSalaries() {
        return salaryRepo.findAll();
    }

    @Override
    public Salary updateSalary(Salary salary) {
        if (salaryRepo.existsById(salary.getSalaryId())) {
            return salaryRepo.save(salary);
        }
        return null;
    }

    @Override
    public void deleteSalary(int salaryId) {
        if (salaryRepo.existsById(salaryId)) {
            salaryRepo.deleteById(salaryId);
        }
    }
}