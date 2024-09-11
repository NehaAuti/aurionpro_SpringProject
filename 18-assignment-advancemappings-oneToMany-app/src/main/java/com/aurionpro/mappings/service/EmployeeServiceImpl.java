package com.aurionpro.mappings.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aurionpro.mappings.entity.Employee;
import com.aurionpro.mappings.entity.SalaryAccount;
import com.aurionpro.mappings.exception.ResourceNotFoundException;
import com.aurionpro.mappings.entity.Client;
import com.aurionpro.mappings.repository.EmployeeRepository;
import com.aurionpro.mappings.repository.SalaryAccountRepository;
import com.aurionpro.mappings.repository.ClientRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ClientRepository clientRepository;
    
    @Autowired
    private SalaryAccountRepository salaryAccountRepository;

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee addEmployeeToClient(int clientId, Employee employee) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        employee.setClient(client);
        return employeeRepository.save(employee);
    }
    
    @Override
    public Employee getEmployeeById(int employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    
    @Override
    public Employee assignSalaryAccountToEmployee(int employeeId, int accountNumber) {
        // Find the employee by ID
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if (!optionalEmployee.isPresent()) {
            throw new ResourceNotFoundException("Employee not found");
        }
        Employee employee = optionalEmployee.get();

        // Find the salary account by account number
        Optional<SalaryAccount> optionalSalaryAccount = salaryAccountRepository.findById(accountNumber);
        if (!optionalSalaryAccount.isPresent()) {
            throw new ResourceNotFoundException("Salary Account not found");
        }
        SalaryAccount salaryAccount = optionalSalaryAccount.get();

        // Assign the salary account to the employee
        employee.setSalaryAccount(salaryAccount);

        // Save and return the updated employee
        return employeeRepository.save(employee);
    }

}