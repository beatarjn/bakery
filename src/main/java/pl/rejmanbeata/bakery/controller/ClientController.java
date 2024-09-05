package pl.rejmanbeata.bakery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.rejmanbeata.bakery.database.ClientEntity;
import pl.rejmanbeata.bakery.mapper.ClientMapper;
import pl.rejmanbeata.bakery.model.client.Client;
import pl.rejmanbeata.bakery.service.ClientService;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    public ClientController(ClientMapper clientMapper, ClientService clientService) {
        this.clientService = clientService;
        this.clientMapper = clientMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable("id") Long id) {
        Client client = clientService.getClientById(id);
        return client == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(client, OK);
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        ClientEntity clientEntity = clientMapper.clientToClientEntity(client);
        clientService.save(clientEntity);
        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable("id") Long id, @RequestBody Client updatedClient) {
        ClientEntity clientEntity = clientMapper.clientToClientEntity(updatedClient);
        Client savedClient = clientService.updateClient(id, clientEntity);
        return savedClient != null ? new ResponseEntity<>(savedClient, OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable("id") Long id) {
        Client existingClient = clientService.getClientById(id);
        if (existingClient != null) {
            clientService.deleteClientById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        return new ResponseEntity<>(clientService.getAllClients(), OK);
    }
}