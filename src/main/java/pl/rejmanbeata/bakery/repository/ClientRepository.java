package pl.rejmanbeata.bakery.repository;

import pl.rejmanbeata.bakery.model.Address;
import pl.rejmanbeata.bakery.model.client.Client;

import java.util.List;

public interface ClientRepository {
    List<Client> findAll();
    Client findByName(String name);
    void saveNewClient(String name, String lastName, Address address);
    void deleteClient(String name);
    Client updateClient(String name, String newName, String newLastName, Address newAddress);
}
