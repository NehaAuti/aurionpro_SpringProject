package com.aurionpro.mappings.service;


import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.mappings.dto.EmployeeDto;
import com.aurionpro.mappings.entity.Client;
import com.aurionpro.mappings.entity.Employee;
import com.aurionpro.mappings.entity.SalaryAccount;
import com.aurionpro.mappings.repository.ClientRepository;
import com.aurionpro.mappings.repository.EmployeeRepository;
import com.aurionpro.mappings.repository.SalaryAccountRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
    private EmployeeRepository employeeRepository;
	@Autowired
    private SalaryAccountRepository salaryAccountRepository;
	@Autowired
    private final ClientRepository clientRepository;

    @Override
    public Employee addEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setPhoneNumber(employeeDto.getPhoneNumber());
        employee.setEmail(employeeDto.getEmail());
        employee.setPosition(employeeDto.getPosition());
        employee.setHireDate(employeeDto.getHireDate());
        employee.setSalary(employeeDto.getSalary());
        employee.setStatus(employeeDto.getStatus());
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(int employeeId, EmployeeDto employeeDto) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setFirstName(employeeDto.getFirstName());
            employee.setLastName(employeeDto.getLastName());
            employee.setPhoneNumber(employeeDto.getPhoneNumber());
            employee.setEmail(employeeDto.getEmail());
            employee.setPosition(employeeDto.getPosition());
            employee.setHireDate(employeeDto.getHireDate());
            employee.setSalary(employeeDto.getSalary());
            employee.setStatus(employeeDto.getStatus());
            return employeeRepository.save(employee);
        }
        return null; 
    }

    @Override
    public void deleteEmployee(int employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        return employeeRepository.findById(employeeId).orElse(null); 
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee assignSalaryAccount(int employeeId, String salaryAccountNumber) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        Optional<SalaryAccount> optionalSalaryAccount = salaryAccountRepository.findById(salaryAccountNumber);
        
        if (optionalEmployee.isPresent() && optionalSalaryAccount.isPresent()) {
            Employee employee = optionalEmployee.get();
            SalaryAccount salaryAccount = optionalSalaryAccount.get();
            employee.setSalaryAccount(salaryAccount);
            return employeeRepository.save(employee);
        }
        return null; 
    }

    @Override
    public Employee assignClient(int employeeId, int clientId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        Optional<Client> optionalClient = clientRepository.findById(clientId);
        
        if (optionalEmployee.isPresent() && optionalClient.isPresent()) {
            Employee employee = optionalEmployee.get();
            Client client = optionalClient.get();
            employee.setClient(client);
            return employeeRepository.save(employee);
        }
        return null;
    }
}
