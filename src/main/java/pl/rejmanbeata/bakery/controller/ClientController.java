package pl.rejmanbeata.bakery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.rejmanbeata.bakery.mapper.ClientMapper;
import pl.rejmanbeata.bakery.model.client.Client;
import pl.rejmanbeata.bakery.service.ClientService;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientMapper clientMapper;

//    @Autowired
//    public ClientController(ClientMapper clientMapper, ClientService clientService) {
//        this.clientService = clientService;
//        this.clientMapper = clientMapper;
//    }

    @GetMapping("/{name}")
    public ResponseEntity<Client> getClientByName(@PathVariable String name) {
        Client client = clientMapper.clientEntityToClient(clientService.findByLastName(name));
        return new ResponseEntity<>(client, OK);
    }

//    @PostMapping
//    public ResponseEntity<Client> createClient(@RequestBody Client client) {
//        clientService.add(client);
//        return new ResponseEntity<>(client, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/{name}")
//    public ResponseEntity<Client> updateClient(@PathVariable String name, @RequestBody Client updatedClient) {
//        Optional<Client> existingClient = clientService.stream()
//                .filter(cli -> cli.getName().equalsIgnoreCase(name))
//                .findFirst();
//
//        if (existingClient.isPresent()) {
//            Client cli = existingClient.get();
//            cli.setLastName(updatedClient.getLastName());
//            cli.setAddress(updatedClient.getAddress());
//            return new ResponseEntity<>(cli, OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @DeleteMapping("/{name}")
//    public ResponseEntity<Void> deleteClient(@PathVariable String name) {
//        Optional<Client> existingClient = clientService.stream()
//                .filter(cli -> cli.getName().equalsIgnoreCase(name))
//                .findFirst();
//
//        if (existingClient.isPresent()) {
//            clientService.remove(existingClient.get());
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Client>> getAllClients() {
//        return new ResponseEntity<>(clientService, OK);
//    }
}

