package demo.controller;

import demo.entity.Client;
import demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController //This means that this class is a controller (also a bean)
@RequestMapping(path = "/demo/clients/") //This means URLs with /demo
@CrossOrigin(origins = "", allowedHeaders = "") //Allows any requests from browser
public class ClientController {
    @Autowired
    private ClientService clientService;

    //GetMapping annotation is used to mark a Java method which
    //represents GET HTTP verb (read operation from CRUD database operations)
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Client> getAllClients(){
        return clientService.findAllClients();
    }

    //A PathVariable is a part of a request URL and can be used when you want to be able to fetch a different resource
    //based on a parameter
    @GetMapping(path = "/id/{client_id}")
    public @ResponseBody Client getClientByID(@PathVariable("client_id") Integer clientID){
        try{
            return clientService.findClientByID(clientID);
        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
        }
    }

    @GetMapping(path = "/name/{client_name}")
    public @ResponseBody Client getClientByName(@PathVariable("client_name") String clientName){
        try{
            return clientService.findClientByName(clientName);
        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
        }
    }

    @DeleteMapping(path = "/delete/id/{id}")
    public @ResponseBody Client deleteClientById(@PathVariable("id") Integer id){
        try{
            return clientService.deleteClientById(id);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
        }

    }

    @DeleteMapping(path = "/delete/name/{name}")
    public @ResponseBody Client deleteClientByName(@PathVariable("name") String name){
        try{
            return clientService.deleteClientByName(name);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
        }

    }

    //TODO: add details about @PathVariable in the file + use the DTOs
}
