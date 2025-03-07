package demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import demo.dto.CreateClientDTO;
import demo.dto.DisplayClientDTO;
import demo.errorhandling.*;
import demo.service.ClientService;
import demo.transformers.CreateToDisplayClientTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.net.URI;
import java.net.URISyntaxException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

@RestController //This means that this class is a controller (also a bean)
@RequestMapping(path = "/demo/clients/") //This means URLs with /demo
@CrossOrigin(origins = "", allowedHeaders = "") //Allows any requests from browser
public class ClientController {
    @Autowired
    private ClientService clientService;

    @Autowired
    private CreateToDisplayClientTransformer createToDisplayClientTransformer;

    //GetMapping annotation is used to mark a Java method which
    //represents GET HTTP verb (read operation from CRUD database operations)
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<DisplayClientDTO> getAllClients() {
        return clientService.findAllClients();
    }

    //A PathVariable is a part of a request URL and can be used when you want to be able to fetch a different resource
    //based on a parameter
    @GetMapping(path = "/id/{client_id}")
    public @ResponseBody DisplayClientDTO getClientByID(@PathVariable("client_id") Integer clientID) {
        try {
            return clientService.findClientByID(clientID);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @GetMapping(path = "/name/{client_name}")
    public @ResponseBody DisplayClientDTO getClientByName(@PathVariable("client_name") String clientName) {
        try {
            return clientService.findClientByName(clientName);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @DeleteMapping(path = "/delete/id/{id}")
    public @ResponseBody DisplayClientDTO deleteClientById(@PathVariable("id") Integer id) {
        try {
            return clientService.deleteClientById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }

    }

    @DeleteMapping(path = "/delete/name/{name}")
    public @ResponseBody DisplayClientDTO deleteClientByName(@PathVariable("name") String name) {
        try {
            return clientService.deleteClientByName(name);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }

    }

    //This is the unique correct implementation for a POST request - using a @RequestBody to send a data
    @PostMapping(path = "/add")
    public ResponseEntity<DisplayClientDTO> createClient(@RequestBody CreateClientDTO createClientDTO) {
        try {
            CreateClientDTO savedClientDTO = clientService.saveClient(createClientDTO);
            DisplayClientDTO displayClientDTO = createToDisplayClientTransformer.transform(savedClientDTO);
            return ResponseEntity.created(new URI("/clients/" + savedClientDTO.getId())).body(displayClientDTO);
        } catch (URISyntaxException e) {
            return ResponseEntity.notFound().build();
        } catch (DuplicatedClientUsernameException exception) {
            System.out.println(exception.getMessage());
            return ResponseEntity.unprocessableEntity().build();
            //return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception.getMessage());
        }
    }


    //Using @PathVariables to send data for a post mapping is not a valid option (even if it's possible to do it)
    //because it can expose sensitive data in the URL
    @PostMapping(path = "/add/path-variable/{name}/{address}/{age}/{username}/{password}")
    public ResponseEntity<DisplayClientDTO> createClientWithPathVariables(@PathVariable("name") String name,
                                                                          @PathVariable("address") String address,
                                                                          @PathVariable("age") Integer age,
                                                                          @PathVariable("username") String username,
                                                                          @PathVariable("password") String password) {
        CreateClientDTO clientDTO = CreateClientDTO.builder()
                .name(name)
                .clientAddress(address)
                .age(age)
                .username(username)
                .password(password)
                .build();
        try {
            CreateClientDTO savedClientDTO = clientService.saveClient(clientDTO);
            DisplayClientDTO displayClientDTO = createToDisplayClientTransformer.transform(savedClientDTO);
            return ResponseEntity.created(new URI("/clients/" + savedClientDTO.getId())).body(displayClientDTO);
        } catch (URISyntaxException | DuplicatedClientUsernameException e) {
            return ResponseEntity.notFound().build();
        }
    }


    //Sending data with @RequestParam on a POST request is not a valid option because, as in the case of @PathVariable,
    //it can expose sensitive data in the URL
    @PostMapping(path = "/add/request-params")
    public ResponseEntity<DisplayClientDTO> createClientWithRequestParams(@RequestParam String name,
                                                                          @RequestParam String address,
                                                                          @RequestParam Integer age,
                                                                          @RequestParam String username,
                                                                          @RequestParam String password) {

        CreateClientDTO clientDTO = CreateClientDTO.builder()
                .name(name)
                .clientAddress(address)
                .age(age)
                .username(username)
                .password(password)
                .build();

        try {
            CreateClientDTO savedClientDTO = clientService.saveClient(clientDTO);
            DisplayClientDTO displayClientDTO = createToDisplayClientTransformer.transform(savedClientDTO);
            return ResponseEntity.created(new URI("/clients/" + savedClientDTO.getId())).body(displayClientDTO);
        } catch (URISyntaxException | DuplicatedClientUsernameException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // @PutMapping is for updating an existing resource, but we need to send the entire object on the request
    // (containing both the modified fields and the unmodified fields)
    @PutMapping(path = "/update/{id}")
    public ResponseEntity<DisplayClientDTO> updateClient(@RequestBody CreateClientDTO updatedClientDTO,
                                                         @PathVariable("id") Integer id) {
        try {
            CreateClientDTO dbUpdatedClientDTO = clientService.updateClient(id, updatedClientDTO);
            DisplayClientDTO displayClientDTO = createToDisplayClientTransformer.transform(dbUpdatedClientDTO);
            return ResponseEntity.created(new URI("/clients/" + dbUpdatedClientDTO.getId())).body(displayClientDTO);
        } catch (URISyntaxException | ClientNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (DuplicatedClientUsernameException exception) {
            System.out.println(exception.getMessage());
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @PatchMapping(path = "/patch/{id}", consumes = "application/json-patch+json")
    public @ResponseBody DisplayClientDTO patchClient(@PathVariable("id") Integer id,
                                                      @RequestBody JsonPatch jsonPatch) {
        try {
            return clientService.patchClient(id, jsonPatch);
        } catch (ClientNotFoundException e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (JsonPatchException | JsonProcessingException exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), exception);
        }
    }

    @DeleteMapping(path = "/delete/client/credit_card/{client_id}")
    public @ResponseBody DisplayClientDTO deleteClientCreditCard(@PathVariable("client_id") Integer clientId) {
        try {
            return clientService.deleteCreditCardByClientId(clientId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }

    }

    @PutMapping(path = "/rentBook/{client_id}/{book_id}")
    public @ResponseBody DisplayClientDTO rentABook(@PathVariable("client_id") Integer clientId, @PathVariable("book_id") Integer bookId) {
        try {
            return clientService.rentABook(clientId, bookId);
        } catch (BookNotFoundException | ClientNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (BookAlreadyBorrowedException | MultipleBookException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @PutMapping(path = "/returnBook/{client_id}")
    public @ResponseBody DisplayClientDTO returnBook(@PathVariable("client_id") Integer clientId) {
        try {
            return clientService.returnBook(clientId);
        } catch (ClientNotFoundException | BookNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}


