package com.aurionpro.mappings.service;
import com.aurionpro.mappings.entity.Client;
import com.aurionpro.mappings.entity.Employee;
import com.aurionpro.mappings.repository.ClientRepository;
import com.aurionpro.mappings.repository.EmployeeRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {
	@Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public Client saveClientWithEmployees(Client client) {
        // Ensure each employee is associated with the client
        if (client.getEmployees() != null) {
            for (Employee employee : client.getEmployees()) {
                employee.setClient(client);
            }
        }
        // Save the client, which will also save associated employees due to cascade settings
        return clientRepository.save(client);
    }
    

    @Override
    public List<Client> getAllClient() {
        return clientRepository.findAll();
    }
	@Override
	public Client getClientrById(int clientId) {
		return clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));
	}


	@Override
	public Client addClient(Client client) {
		// TODO Auto-generated method stub
		return clientRepository.save(client);
	}


	
}


