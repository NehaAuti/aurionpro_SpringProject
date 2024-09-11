package com.aurionpro.mappings.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.mappings.dto.SalaryDto;
import com.aurionpro.mappings.entity.Salary;
import com.aurionpro.mappings.entity.SalaryTransaction;
import com.aurionpro.mappings.exceptions.ResourceNotFoundException;
import com.aurionpro.mappings.repository.SalaryRepository;
import com.aurionpro.mappings.repository.SalaryTransactionRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SalaryServiceImpl implements SalaryService {

	@Autowired
    private  SalaryRepository salaryRepository;
	@Autowired
	private	SalaryTransactionRepository salaryTransactionRepository;

    @Override
    public Salary addSalary(SalaryDto salaryDto) {
        Salary salary = new Salary();
        salary.setSalaryMonth(salaryDto.getSalaryMonth());
        salary.setGrossSalary(salaryDto.getGrossSalary());
        salary.setDeductions(salaryDto.getDeductions());
        salary.setPaymentDate(salaryDto.getPaymentDate());
        salary.setStatus(salaryDto.getStatus());
        salary.setNetSalary(salaryDto.getNetSalary());
        return salaryRepository.save(salary);
    }

    @Override
    public Salary updateSalary(int salaryId, SalaryDto salaryDto) {
        Optional<Salary> optionalSalary = salaryRepository.findById(salaryId);
        if (optionalSalary.isPresent()) {
            Salary salary = optionalSalary.get();
            salary.setSalaryMonth(salaryDto.getSalaryMonth());
            salary.setGrossSalary(salaryDto.getGrossSalary());
            salary.setDeductions(salaryDto.getDeductions());
            salary.setPaymentDate(salaryDto.getPaymentDate());
            salary.setStatus(salaryDto.getStatus());
            salary.setNetSalary(salaryDto.getNetSalary());
            return salaryRepository.save(salary);
        }
        return null; 
    }

    @Override
    public void deleteSalary(int salaryId) {
        salaryRepository.deleteById(salaryId);
    }

    @Override
    public Salary getSalaryById(int salaryId) {
        return salaryRepository.findById(salaryId).orElse(null);
    }

    @Override
    public List<Salary> getAllSalaries() {
        return salaryRepository.findAll();
    }
    @Override
    @Transactional
    public Salary allocateSalaryTransactions(int salaryId, List<SalaryTransaction> transactions) {
     
        Salary salary = salaryRepository.findById(salaryId)
                .orElseThrow(() -> new ResourceNotFoundException("Salary record not found with id " + salaryId));
        
      
        for (SalaryTransaction transaction : transactions) {
            transaction.setSalary(salary);
            salaryTransactionRepository.save(transaction);
        }
        
        
        return salaryRepository.save(salary);
        
    }
}
