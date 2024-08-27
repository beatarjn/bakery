package pl.rejmanbeata.bakery.controller;

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
    private ClientService clientService;
    private ClientMapper clientMapper;

    public ClientController(ClientMapper clientMapper, ClientService clientService) {
        this.clientService = clientService;
        this.clientMapper = clientMapper;
    }

    @GetMapping("/{name}")
    public ResponseEntity<Client> getClientByName(@PathVariable String name) {
        Client client = clientMapper.clientEntityToClient(clientService.findByLastName(name));
        return new ResponseEntity<>(client, OK);
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        ClientEntity clientEntity = clientMapper.clientToClientEntity(client);
        clientService.save(clientEntity);
        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client updatedClient) {
        ClientEntity clientEntity = clientMapper.clientToClientEntity(updatedClient);
        ClientEntity savedClient = clientService.updateClient(id, clientEntity);
        if (savedClient != null) {
            return new ResponseEntity<>(updatedClient, OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        ClientEntity existingClient = clientService.getClientById(id);

        if (existingClient != null) {
            clientService.deleteClientById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.getAllClients()
                .stream()
                .map(c -> clientMapper.clientEntityToClient(c))
                .toList();
        return new ResponseEntity<>(clients, OK);
    }
}