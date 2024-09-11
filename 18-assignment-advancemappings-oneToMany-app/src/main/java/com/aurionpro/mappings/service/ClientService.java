package com.aurionpro.mappings.service;

import java.util.List;

import com.aurionpro.mappings.entity.Client;

public interface ClientService {
	Client saveClientWithEmployees(Client client);
	Client addClient(Client client);
    List<Client> getAllClient();
    Client getClientrById(int clientId);

}
