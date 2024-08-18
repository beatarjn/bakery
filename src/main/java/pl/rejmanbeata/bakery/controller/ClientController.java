package pl.rejmanbeata.bakery.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.rejmanbeata.bakery.model.client.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final List<Client> clientList = new ArrayList<>();

    @GetMapping("/{name}")
    public ResponseEntity<Client> getClientByName(@PathVariable String name) {
        Optional<Client> client = clientList.stream()
                .filter(cli -> cli.getName().equalsIgnoreCase(name))
                .findFirst();

        return client
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        clientList.add(client);
        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

    @PutMapping("/{name}")
    public ResponseEntity<Client> updateClient(@PathVariable String name, @RequestBody Client updatedClient) {
        Optional<Client> existingClient = clientList.stream()
                .filter(cli -> cli.getName().equalsIgnoreCase(name))
                .findFirst();

        if (existingClient.isPresent()) {
            Client cli = existingClient.get();
            cli.setLastName(updatedClient.getLastName());
            cli.setAddress(updatedClient.getAddress());
            return new ResponseEntity<>(cli, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteClient(@PathVariable String name) {
        Optional<Client> existingClient = clientList.stream()
                .filter(cli -> cli.getName().equalsIgnoreCase(name))
                .findFirst();

        if (existingClient.isPresent()) {
            clientList.remove(existingClient.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        return new ResponseEntity<>(clientList, HttpStatus.OK);
    }
}

