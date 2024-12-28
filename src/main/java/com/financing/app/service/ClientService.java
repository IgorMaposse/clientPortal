package com.financing.app.service;

import com.financing.app.models.Client;

import java.util.List;

public interface ClientService {

	
	public Client save(Client client,String performedBy);
	public  String add();
	public  void delete(long id,String performedBy);
	public  Client update(Client client, long id,String performedBy);
	public  Client select(long id);
	public List<Client> ClientList();
	List<Client> ClientList(String partialId);
	public List<Client> getApprovedClients();
	public List<Client> getPendingClients();


}
