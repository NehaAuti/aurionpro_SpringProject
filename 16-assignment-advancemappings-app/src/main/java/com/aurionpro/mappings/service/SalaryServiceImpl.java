package com.aurionpro.mappings.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.mappings.entity.Salary;
import com.aurionpro.mappings.entity.SalaryTransaction;
import com.aurionpro.mappings.repository.SalaryRepository;
import com.aurionpro.mappings.dto.SalaryDTO;
import com.aurionpro.mappings.dto.SalaryTransactionDTO;
import com.aurionpro.mappings.dto.PageResponse;

@Service
public class SalaryServiceImpl implements SalaryService {

    @Autowired
    private SalaryRepository salaryRepo;

    @Override
    public PageResponse<Salary> getAllSalaries(int pageno, int pagesize) {
        Pageable pageable = PageRequest.of(pageno, pagesize);
        Page<Salary> salaryPage = salaryRepo.findAll(pageable);

        // Create a PageResponse<Salary> object
        PageResponse<Salary> salaryPageResponse = new PageResponse<>();
        salaryPageResponse.setTotalPages(salaryPage.getTotalPages());
        salaryPageResponse.setSize(salaryPage.getSize());
        salaryPageResponse.setTotalElements(salaryPage.getTotalElements());
        salaryPageResponse.setLastPage(salaryPage.isLast());

        // Set the content of the PageResponse to the list of Salary entities
        salaryPageResponse.setContent(salaryPage.getContent());

        return salaryPageResponse;
    }


    @Override
    public SalaryDTO getSalaryById(int salaryId) {
        Optional<Salary> optionalSalary = salaryRepo.findById(salaryId);
        if (optionalSalary.isPresent()) {
            Salary salary = optionalSalary.get();
            return new SalaryDTO(
                salary.getSalaryId(),
                salary.getSalaryMonth(),
                salary.getGrossSalary(),
                salary.getDeductions(),
                salary.getNetSalary(),
                salary.getPaymentDate(),
                salary.getStatus()
            );
        }
        return null;
    }

    @Override
    public SalaryTransactionDTO getSalaryTransactionById(int salaryId) {
        Optional<Salary> optionalSalary = salaryRepo.findById(salaryId);
        if (optionalSalary.isPresent()) {
            Salary salary = optionalSalary.get();
            SalaryTransaction salaryTransaction = salary.getSalaryTransaction();
            return new SalaryTransactionDTO(
                salaryTransaction.getTransactionId(),
                salaryTransaction.getTransactionDate(),
                salaryTransaction.getAmount(),
                salaryTransaction.getStatus()
            );
        }
        return null;
    }

    
    @Override
    public Salary addSalary(Salary salary) {
        // Calculate net salary before saving
        salary.calculateNetSalary();
        
        // Save the salary entity to the database
        return salaryRepo.save(salary);
    }
    
    @Override
    public boolean partiallyUpdateSalary(int salaryId, SalaryDTO salaryDTO) {
        Optional<Salary> optionalSalary = salaryRepo.findById(salaryId);
        if (optionalSalary.isPresent()) {
            Salary salary = optionalSalary.get();

            if (salaryDTO.getSalaryMonth() != null) {
                salary.setSalaryMonth(salaryDTO.getSalaryMonth());
            }
            if (salaryDTO.getGrossSalary() != null) {
                salary.setGrossSalary(salaryDTO.getGrossSalary());
            }
            if (salaryDTO.getDeductions() != null) {
                salary.setDeductions(salaryDTO.getDeductions());
            }
            if (salaryDTO.getPaymentDate() != null) {
                salary.setPaymentDate(salaryDTO.getPaymentDate());
            }
            if (salaryDTO.getStatus() != null) {
                salary.setStatus(salaryDTO.getStatus());
            }

            salary.calculateNetSalary(); // Ensure netSalary is updated
            salaryRepo.save(salary);
            return true;
        }
        return false;
    }
}