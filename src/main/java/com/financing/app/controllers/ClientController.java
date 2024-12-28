package com.financing.app.controllers;

import com.financing.app.models.Client;
import com.financing.app.models.Loan;
import com.financing.app.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import com.financing.app.service.ClientService;

import jakarta.validation.Valid;

import java.lang.reflect.Method;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@RestController
public class ClientController {
	private Client existingClient;

	@Autowired
	private ClientService clientService;


	   private final Path fileStorageLocation = Paths.get("uploads").toAbsolutePath().normalize();

	    public ClientController() throws IOException {
	        Files.createDirectories(fileStorageLocation); // Ensure directory exists
	    }

	    @PostMapping("/{id}/upload")
	    public String uploadFile(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {
	        String fileName = file.getOriginalFilename();
	        Path targetLocation = fileStorageLocation.resolve(fileName);
	        Files.copy(file.getInputStream(), targetLocation);

	        // Update the client's document details in the database
	        // Assuming you have a ClientService to manage client data
	        // Client client = clientService.findById(id);
	        // client.setDocumentName(fileName);
	        // client.setDocumentPath(targetLocation.toString());
	        // clientService.save(client);

	        return "File uploaded successfully: " + fileName;
	    }
	@PostMapping("/client")
	public ResponseEntity<Client> save( @Valid @RequestBody Client client, String performedBy) {
		

		return new ResponseEntity<Client>(clientService.save(client, performedBy),HttpStatus.CREATED);
	}
	
	@RequestMapping(path="client/{id}", method=RequestMethod.DELETE)
	public void  delete (@PathVariable long id, String performedBy){
		if(clientService.select(id)!=null) {
			clientService.delete(id, performedBy);
		}
	}

	@GetMapping("/client")
	public List<Client> ClientList(){
		List<Client> clients = clientService.ClientList();

		// Format the ID for each client
		clients.forEach(client -> {
			String formattedId =  String.format("%09d", client.getId());
			client.setFormattedId(formattedId);
		});

		return clients;
	}
	@GetMapping("/client/{id}")
	public List<Client> ClientList(@PathVariable String id) {
		List<Client> clients = clientService.ClientList(id);

		if (clients != null && !clients.isEmpty()) {
			clients.forEach(client -> {
				String formattedId = String.format("%09d", client.getId());
				client.setFormattedId(formattedId);
			});
		}

		return clients;
	}

	@PutMapping(value = "client/{id}")
	public Client update(@RequestBody Client client,@PathVariable  long id, String performedBy){

	return  clientService.update(client,id,performedBy);
	}

	@GetMapping("/client/pending")
	public List<Client> getPendingClients() {
		List<Client> clients = clientService.getPendingClients();
		formatClientIds(clients);

		return clients;
	}

	@GetMapping("/client/approved")
	public List<Client> getApprovedClients() {
		List<Client> clients = clientService.getApprovedClients();
		formatClientIds(clients);

		return clients;
	}



	private void formatClientIds(List<Client> clients) {
		if (clients != null && !clients.isEmpty()) {
			clients.forEach(client -> {
				String formattedId
						= String.format("%09d", client.getId());
				client.setFormattedId(formattedId);
			});
		}
	}
}
