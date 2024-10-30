package demo.service;

import demo.entity.Client;
import demo.errorhandling.ClientNotFoundException;

public interface ClientService {
    Iterable<Client> findAllClients();
    Client findClientByID(Integer id) throws ClientNotFoundException;
    Client findClientByName(String name) throws ClientNotFoundException;
    Client deleteClientById(Integer id) throws ClientNotFoundException;
    Client deleteClientByName(String name) throws ClientNotFoundException;

}
