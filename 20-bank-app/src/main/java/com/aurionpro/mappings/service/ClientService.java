package com.aurionpro.mappings.service;

import java.util.List;

import com.aurionpro.mappings.dto.ClientDto;
import com.aurionpro.mappings.entity.Client;
import com.aurionpro.mappings.entity.Employee;


public interface ClientService {
    Client addClient(ClientDto clientDto);
    Client updateClient(int clientId, ClientDto clientDto);
    Client getClientById(int clientId);
    void deleteClient(int clientId);
    Client allocateEmployees(int clientId, List<Employee> employees);
}
