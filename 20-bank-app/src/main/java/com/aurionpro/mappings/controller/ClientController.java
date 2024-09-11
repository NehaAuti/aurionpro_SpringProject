package com.aurionpro.mappings.controller;

import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.mappings.dto.ClientDto;
import com.aurionpro.mappings.entity.Client;
import com.aurionpro.mappings.entity.Employee;
import com.aurionpro.mappings.service.ClientService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/bankapp")
@RequiredArgsConstructor
public class ClientController {

	private final ClientService clientService;

	@PostMapping("/client")
	public ResponseEntity<Client> addClient(@RequestBody ClientDto clientDto) {
		Client client = clientService.addClient(clientDto);
		return ResponseEntity.ok(client);
	}

	@PutMapping("/client/{clientId}")
	public ResponseEntity<Client> updateClient(@PathVariable int clientId, @RequestBody ClientDto clientDto) {
		Client client = clientService.updateClient(clientId, clientDto);
		if (client != null) {
			return ResponseEntity.ok(client);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/client/{clientId}")
	public ResponseEntity<Client> getClientById(@PathVariable int clientId) {
		Client client = clientService.getClientById(clientId);
		if (client != null) {
			return ResponseEntity.ok(client);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/client/{clientId}")
	public ResponseEntity<Void> deleteClient(@PathVariable int clientId) {
		clientService.deleteClient(clientId);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/client/{clientId}/employees")
	public ResponseEntity<Client> allocateEmployees(@PathVariable int clientId, @RequestBody List<Employee> employees) {
		Client client = clientService.allocateEmployees(clientId, employees);
		if (client != null) {
			return ResponseEntity.ok(client);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
