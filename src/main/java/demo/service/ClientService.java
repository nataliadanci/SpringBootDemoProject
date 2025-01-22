package demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import demo.dto.CreateClientDTO;
import demo.dto.DisplayClientDTO;
import demo.entity.Client;
import demo.errorhandling.ClientNotFoundException;
import demo.errorhandling.CreditCardNotFoundException;
import demo.errorhandling.DuplicatedClientUsernameException;

import java.sql.SQLIntegrityConstraintViolationException;

public interface ClientService {
    Iterable<DisplayClientDTO> findAllClients();
    DisplayClientDTO findClientByID(Integer id) throws ClientNotFoundException;
    DisplayClientDTO findClientByName(String name) throws ClientNotFoundException;
    DisplayClientDTO deleteClientById(Integer id) throws ClientNotFoundException;
    DisplayClientDTO deleteClientByName(String name) throws ClientNotFoundException;
    CreateClientDTO saveClient(CreateClientDTO createClientDTO) throws DuplicatedClientUsernameException;
    CreateClientDTO updateClient(Integer id, CreateClientDTO createClientDTO) throws ClientNotFoundException, DuplicatedClientUsernameException;
    DisplayClientDTO patchClient(Integer id, JsonPatch jsonPatch) throws ClientNotFoundException, JsonPatchException, JsonProcessingException;
    DisplayClientDTO deleteCreditCardByClientId(Integer client_id) throws ClientNotFoundException, CreditCardNotFoundException;
}
