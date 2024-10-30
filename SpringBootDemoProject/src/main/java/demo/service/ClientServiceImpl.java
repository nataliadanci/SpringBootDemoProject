package demo.service;

import demo.entity.Client;
import demo.errorhandling.ClientNotFoundException;
import demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    //With the Autowired annotation we inject in this class a bean (an instance object which was already created)
    @Autowired
    private ClientRepository clientRepository;
    @Override
    public Iterable<Client> findAllClients(){
        return clientRepository.findAll();

    }

    @Override
    public Client findClientByID(Integer id) throws ClientNotFoundException {
        Optional<Client> optionalClient = clientRepository.findById(id);
        Client foundClient = optionalClient.orElseThrow(()->new ClientNotFoundException("No client found with this ID."));
        return foundClient;
    }
    @Override
    public Client findClientByName(String name) throws ClientNotFoundException {
        Optional<Client> optionalClient = clientRepository.findByName(name);
        Client foundClient = optionalClient.orElseThrow(()->new ClientNotFoundException("No client found with this name."));
        return foundClient;
    }
    @Override
    public Client deleteClientById(Integer id) throws ClientNotFoundException{
        Optional<Client> clientToDelete = clientRepository.findById(id);
        Client foundClientDeleted = clientToDelete.orElseThrow(()-> new ClientNotFoundException("No client found with this ID."));
        clientRepository.delete(foundClientDeleted);
        return foundClientDeleted;
    }

    @Override
    public Client deleteClientByName(String name) throws ClientNotFoundException{
        Optional<Client> clientToDelete = clientRepository.findByName(name);
        Client foundClientDeleted = clientToDelete.orElseThrow(()-> new ClientNotFoundException("No client found with this name."));
        clientRepository.delete(foundClientDeleted);
        return foundClientDeleted;
    }

}
