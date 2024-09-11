package com.aurionpro.mappings.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.mappings.entity.Employee;
import com.aurionpro.mappings.entity.SalaryAccount;
import com.aurionpro.mappings.repository.EmployeeRepository;
import com.aurionpro.mappings.dto.EmployeeDTO;
import com.aurionpro.mappings.dto.PageResponse;
import com.aurionpro.mappings.dto.SalaryAccountDTO;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepo;

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    @Override
    public PageResponse<Employee> getAllEmployees(int pageno, int pagesize) {
        Pageable pageable = PageRequest.of(pageno, pagesize);
        Page<Employee> employeePage = employeeRepo.findAll(pageable);
        
        PageResponse<Employee> employeePageResponse = new PageResponse<>();
        employeePageResponse.setTotalPages(employeePage.getTotalPages());
        employeePageResponse.setSize(employeePage.getSize());
        employeePageResponse.setTotalElements(employeePage.getTotalElements());
        employeePageResponse.setLastPage(employeePage.isLast());
        employeePageResponse.setContent(employeePage.getContent());
        return employeePageResponse;
    }

    @Override
    public EmployeeDTO getEmployeeById(int employeeId) {
        Employee employee = employeeRepo.findById(employeeId).orElse(null);
        if (employee != null) {
            return new EmployeeDTO(employee.getEmployeeId(), employee.getFirstName(), employee.getLastName(), employee.getPhoneNumber(), employee.getEmail(), employee.getPosition(), employee.getHireDate(), employee.getSalary(), employee.getStatus());
        }
        return null;
    }

    @Override
    public SalaryAccountDTO getEmployeeSalaryAccountById(int employeeId) {
        Employee employee = employeeRepo.findById(employeeId).orElse(null);
        if (employee != null) {
            SalaryAccount salaryAccount = employee.getSalaryAccount();
            return new SalaryAccountDTO(salaryAccount.getAccountNumber(), salaryAccount.getAccountHolderName());
        }
        return null;
    }

    @Override
    public boolean partiallyUpdateEmployee(int employeeId, EmployeeDTO employeeDTO) {
        // Find the employee by ID
        Employee employee = employeeRepo.findById(employeeId).orElse(null);
        if (employee != null) {
            // Update only the fields provided in the EmployeeDTO
            if (employeeDTO.getFirstName() != null) {
                employee.setFirstName(employeeDTO.getFirstName());
            }
            if (employeeDTO.getLastName() != null) {
                employee.setLastName(employeeDTO.getLastName());
            }
            if (employeeDTO.getPhoneNumber() != null) {
                employee.setPhoneNumber(employeeDTO.getPhoneNumber());
            }
            if (employeeDTO.getEmail() != null) {
                employee.setEmail(employeeDTO.getEmail());
            }
            if (employeeDTO.getPosition() != null) {
                employee.setPosition(employeeDTO.getPosition());
            }
            if (employeeDTO.getSalary() != null) {
                employee.setSalary(employeeDTO.getSalary());
            }
            if (employeeDTO.getStatus() != null) {
                employee.setStatus(employeeDTO.getStatus());
            }

            // Save the updated employee
            employeeRepo.save(employee);
            return true;
        }
        return false;
    }
}