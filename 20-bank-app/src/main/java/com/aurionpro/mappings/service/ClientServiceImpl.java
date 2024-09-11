package com.aurionpro.mappings.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.mappings.dto.ClientDto;
import com.aurionpro.mappings.entity.Client;
import com.aurionpro.mappings.entity.Employee;
import com.aurionpro.mappings.repository.ClientRepository;
import com.aurionpro.mappings.repository.EmployeeRepository;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Client addClient(ClientDto clientDto) {
        Client client = new Client();
        client.setCompanyName(clientDto.getCompanyName());
        client.setRegistrationNumber(clientDto.getRegistrationNumber());
        client.setContactPerson(clientDto.getContactPerson());
        client.setContactNumber(clientDto.getContactNumber());
        client.setAddress(clientDto.getAddress());
        client.setCreationDate(clientDto.getCreationDate());
        client.setKycStatus(clientDto.getKycStatus());

        if (clientDto.getEmployees() != null) {
            List<Employee> employees = clientDto.getEmployees().stream()
                .map(employeeDto -> {
                    Employee employee = new Employee();
                    employee.setEmployeeId(employeeDto.getEmployeeId());
                    employee.setFirstName(employeeDto.getFirstName());
                    employee.setLastName(employeeDto.getLastName());
                    employee.setPhoneNumber(employeeDto.getPhoneNumber());
                    employee.setEmail(employeeDto.getEmail());
                    employee.setPosition(employeeDto.getPosition());
                    employee.setHireDate(employeeDto.getHireDate());
                    employee.setSalary(employeeDto.getSalary());
                    employee.setStatus(employeeDto.getStatus());
                    employee.setClient(client);
                    return employee;
                })
                .collect(Collectors.toList());
            client.setEmployees(employees);
        }

        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(int clientId, ClientDto clientDto) {
        Optional<Client> optionalClient = clientRepository.findById(clientId);
        if (!optionalClient.isPresent()) {
            return null; 
        }
        Client client = optionalClient.get();
        client.setCompanyName(clientDto.getCompanyName());
        client.setRegistrationNumber(clientDto.getRegistrationNumber());
        client.setContactPerson(clientDto.getContactPerson());
        client.setContactNumber(clientDto.getContactNumber());
        client.setAddress(clientDto.getAddress());
        client.setCreationDate(clientDto.getCreationDate());
        client.setKycStatus(clientDto.getKycStatus());

        if (clientDto.getEmployees() != null) {
            List<Employee> employees = clientDto.getEmployees().stream()
                .map(employeeDto -> {
                    Employee employee = new Employee();
                    employee.setEmployeeId(employeeDto.getEmployeeId());
                    employee.setFirstName(employeeDto.getFirstName());
                    employee.setLastName(employeeDto.getLastName());
                    employee.setPhoneNumber(employeeDto.getPhoneNumber());
                    employee.setEmail(employeeDto.getEmail());
                    employee.setPosition(employeeDto.getPosition());
                    employee.setHireDate(employeeDto.getHireDate());
                    employee.setSalary(employeeDto.getSalary());
                    employee.setStatus(employeeDto.getStatus());
                    employee.setClient(client);
                    return employee;
                })
                .collect(Collectors.toList());
            client.setEmployees(employees);
        }

        return clientRepository.save(client);
    }

    @Override
    public Client getClientById(int clientId) {
        return clientRepository.findById(clientId).orElse(null); 
    }

    @Override
    public void deleteClient(int clientId) {
        clientRepository.deleteById(clientId);
    }

    @Override
    public Client allocateEmployees(int clientId, List<Employee> employees) {
        Optional<Client> optionalClient = clientRepository.findById(clientId);
        if (!optionalClient.isPresent()) {
            return null; 
        }
        Client client = optionalClient.get();
        List<Employee> dbEmployees = employees.stream()
                .map(employee -> {
                    Optional<Employee> optionalEmployee = employeeRepository.findById(employee.getEmployeeId());
                    if (optionalEmployee.isPresent()) {
                        Employee dbEmployee = optionalEmployee.get();
                        dbEmployee.setClient(client);
                        return dbEmployee;
                    }
                    return null;
                })
                .filter(e -> e != null)
                .collect(Collectors.toList());

        client.setEmployees(dbEmployees);
        return clientRepository.save(client);
    }
}
