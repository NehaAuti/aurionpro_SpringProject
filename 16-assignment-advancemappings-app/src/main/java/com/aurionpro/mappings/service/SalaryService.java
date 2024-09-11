package com.aurionpro.mappings.service;

import java.util.List;

import com.aurionpro.mappings.dto.PageResponse;
import com.aurionpro.mappings.dto.SalaryDTO;
import com.aurionpro.mappings.dto.SalaryTransactionDTO;
import com.aurionpro.mappings.entity.Salary;
import com.aurionpro.mappings.entity.SalaryTransaction;

public interface SalaryService {
	PageResponse<Salary> getAllSalaries(int pageno, int pagesize);
    SalaryDTO getSalaryById(int salaryId);
    Salary addSalary(Salary salary);;
    SalaryTransactionDTO getSalaryTransactionById(int salaryId);
    boolean partiallyUpdateSalary(int salaryId, SalaryDTO salaryDTO);

}
