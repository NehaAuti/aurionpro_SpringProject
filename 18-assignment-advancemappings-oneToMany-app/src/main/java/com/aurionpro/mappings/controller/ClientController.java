package com.aurionpro.mappings.controller;

import com.aurionpro.mappings.entity.Client;
import com.aurionpro.mappings.service.ClientService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bankapp")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/client/employee")
    public ResponseEntity<Client> saveClientWithEmployees(@RequestBody Client client) {
        Client savedClient = clientService.saveClientWithEmployees(client);
        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }
    @GetMapping("clients/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable("id") int clientId) {
        Client client = clientService.getClientrById(clientId);
        return ResponseEntity.ok(client);
    }

    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getAllClient() {
        List<Client> client = clientService.getAllClient();
        return ResponseEntity.ok(client);
    }
    
    @PostMapping("/client")
    public ResponseEntity<Client> addClient(@RequestBody Client client) {
        Client savedClient = clientService.addClient(client);
        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }
}