package com.financing.app.serviceImpl;

import com.financing.app.Enum.State;

import com.financing.app.models.Client;
import com.financing.app.models.Loan;
import com.financing.app.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.financing.app.service.ClientService;
import com.financing.app.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService{



	@Autowired
	private ClientRepository clientRepository;

	private Client existingClient;
	
	@Autowired
	private UserService userService;
	
	 @Autowired
	 private ActionLogService actionLogService;
	 
	 
	 public ClientServiceImpl(ClientRepository clientRepository) {
	        this.clientRepository = clientRepository;
	    }

	    public Client saveFileMetadata(Long clientId, String fileName, String filePath) {
	        Client client = clientRepository.findById(clientId).orElseThrow(() -> new RuntimeException("Client not found"));
	        client.setDocumentName(fileName);
	        client.setDocumentPath(filePath);
	        return clientRepository.save(client);
	    }

	@Override
	public Client save(Client client, String performedBy) {
	
		 Client savedClient = clientRepository.save(client);
		// System.out.println(userService.getLoggedInUser().getName());
		actionLogService.logAction("Cliente", savedClient.getId(), "CREATE", "Cliente: " + savedClient.getName(),performedBy,savedClient.getInputter(),savedClient.getAuthorizer(),savedClient.getState());
		
		return savedClient;
	}
	

	@Override
	public String add() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Client> ClientList(String partialId) {
		// Implementação para retornar clientes com base no partialId
		return clientRepository.findByIdContaining(partialId);
	}
	@Override
	public void delete(long id,String performedBy) {
		// TODO Auto-generated method stub
		Client client= clientRepository.findById(id).orElseThrow();
	//	client.setState(State.PENDING);
        clientRepository.deleteById(id);
		actionLogService.logAction("Cliente", client.getId(), "DELETE", "Cliente : " + client.getName(),  performedBy,client.getInputter(),client.getAuthorizer(),client.getState());
    }
	@Override
	public List<Client> getApprovedClients() {
		return clientRepository.findByState(State.APPROVED);
	}
	@Override
	public List<Client> getPendingClients() {
		return clientRepository.findByState(State.PENDING);
	}

	@Override
	public  Client select( long id){
		Client client= clientRepository.findById(id).orElseThrow();
		//client.setState(State.PENDING);
		return clientRepository.getById(id);
	}

	@Override
	public List<Client> ClientList() {
		
		return clientRepository.findAll();
	}

	@Override
	public Client update(Client client, long id,String performedBy) {
		// TODO Auto-generated metghod stub
	
		//client.setState(State.PENDING);
		existingClient= clientRepository.findById(id).orElse(null);
		existingClient.setAddress(client.getAddress());
		existingClient.setBirthDate(client.getBirthDate());
		existingClient.setCity(client.getCity());
		existingClient.setContact(client.getContact());
		existingClient.setEmail(client.getEmail());
		existingClient.setEmployer(client.getEmployer());
		existingClient.setContactPersonName(client.getContactPersonName());
		existingClient.setContactPersonPhone(client.getContactPersonPhone());
		existingClient.setGender(client.getGender());
		existingClient.setFather_name(client.getFather_name());
		existingClient.setId_Number(client.getId_Number());
		existingClient.setIssueDate(client.getIssueDate());
		existingClient.setMarital_status(client.getMarital_status());
		existingClient.setState(client.getState());
		existingClient.setMother_name(client.getMother_name());
		existingClient.setNationality(client.getNationality());
		existingClient.setNaturality(client.getNaturality());
		existingClient.setPep(client.getPep());
		existingClient.setName(client.getName());
		existingClient.setNuit(client.getNuit());
		existingClient.setProfession(client.getProfession());
		existingClient.setInputter(client.getInputter());
		existingClient.setAuthorizer(client.getAuthorizer());
		existingClient.setSalary(client.getSalary());
		existingClient.setValidationDate(client.getValidationDate());
		existingClient.setClassification(client.getClassification());
		existingClient.setDocumentType(client.getDocumentType());
		existingClient.setCompany(client.getCompany());
		
		Client savedClient= clientRepository.save(existingClient);
		actionLogService.logAction("Cliente", savedClient.getId(), "UPDATE", "Cliente: " + savedClient.getName(),performedBy, savedClient.getInputter(),savedClient.getAuthorizer(),savedClient.getState());


		return savedClient;

	}

}
