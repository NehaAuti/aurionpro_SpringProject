package com.aurionpro.jpacurd.entity.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.jpacurd.entity.Client;
import com.aurionpro.jpacurd.repository.ClientRepository;
@Service
public class ClientServiceImpl implements ClientService{
	@Autowired
	private ClientRepository clientRepo;

	@Override
	public Page<Client> getAllClient(int pageno, int pagesize) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageno,pagesize);
		Page<Client> clientPage = clientRepo.findAll(pageable);
		return clientPage;
		//return clientRepo.findAll();
	}

	@Override
	public void addClient(Client client) {
		// TODO Auto-generated method stub
		clientRepo.save(client);
		
	}

	@Override
	public Optional<Client> getByClientId(int clientId) {
		// TODO Auto-generated method stub
		return clientRepo.findById(clientId);
	}

	@Override
	public void deleteClient(int clientId) {
		// TODO Auto-generated method stub
		clientRepo.deleteById(clientId);
		
	}

	@Override
    public Page<Client> getAllClientsByName(String companyName, int pageno, int pagesize) {
        Pageable pageable = PageRequest.of(pageno, pagesize);
        return clientRepo.findByCompanyName(companyName, pageable);
    }

}
