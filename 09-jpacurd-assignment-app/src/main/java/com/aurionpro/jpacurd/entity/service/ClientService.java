package com.aurionpro.jpacurd.entity.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.aurionpro.jpacurd.entity.Client;


public interface ClientService {
	Page<Client> getAllClient(int pageno , int pagesize);
	void addClient(Client client);
	Optional<Client> getByClientId(int clientId);
	void deleteClient(int clientId);
	Page<Client> getAllClientsByName(String companyName,int pageno,int pagesize);
}
