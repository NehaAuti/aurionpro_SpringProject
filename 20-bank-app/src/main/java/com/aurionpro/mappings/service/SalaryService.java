package com.aurionpro.mappings.service;

import java.util.List;

import com.aurionpro.mappings.dto.SalaryDto;
import com.aurionpro.mappings.entity.Salary;
import com.aurionpro.mappings.entity.SalaryTransaction;


public interface SalaryService {
    Salary addSalary(SalaryDto salaryDto);
    Salary updateSalary(int salaryId, SalaryDto salaryDto);
    void deleteSalary(int salaryId);
    Salary getSalaryById(int salaryId);
    List<Salary> getAllSalaries();
    Salary allocateSalaryTransactions(int salaryId, List<SalaryTransaction> transactions);

}
