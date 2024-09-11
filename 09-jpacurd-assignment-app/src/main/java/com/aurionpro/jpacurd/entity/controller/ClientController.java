package com.aurionpro.jpacurd.entity.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.jpacurd.entity.Client;
import com.aurionpro.jpacurd.entity.service.ClientService;


@RestController
@RequestMapping("/clientapp")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@GetMapping("/client")
	public ResponseEntity<Page<Client>> getAllClient(@RequestParam int pageno, @RequestParam int pagesize)
	{
		return ResponseEntity.ok(clientService.getAllClient(pageno,pagesize));
	}
	
    @GetMapping("client/{clientId}")
    public ResponseEntity<Optional<Client>>  getByClientId(@PathVariable int clientId) 
    {	
    	return ResponseEntity.ok(clientService. getByClientId(clientId));
    }

    @PostMapping("/client")
    public ResponseEntity<String> addClient(@RequestBody Client client) 
    {
        clientService.addClient(client);
        return ResponseEntity.ok("client added successfully");
    }

    @DeleteMapping("client/{clientId}")
    public ResponseEntity<String> deleteClient(@PathVariable int clientId) 
    {

    	 clientService.deleteClient((int) clientId);
         return ResponseEntity.ok("Client deleted sucessfully");
    }
    @GetMapping("/clients")
    public ResponseEntity <Page<Client>> getAllClientsByName(@RequestParam(required = false) String companyName,@RequestParam int pageno, @RequestParam int pagesize)  
    {
    	if(companyName!=null)
    		return ResponseEntity.ok(clientService.getAllClientsByName(companyName,pageno,pagesize));
    	return ResponseEntity.ok(clientService.getAllClient(pageno,pagesize));
    }
    
   
}
