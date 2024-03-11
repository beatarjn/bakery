package pl.rejmanbeata.bakery.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.rejmanbeata.bakery.model.Address;
import pl.rejmanbeata.bakery.model.client.Client;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class ListBasedClientRepository implements ClientRepository {
    private List<Client> clients;

    @Override
    public List<Client> findAll() {
        return clients;
    }

    @Override
    public Client findByName(String name) {
        Optional<Client> client = clients.stream()
                .filter(c -> c.getName().equals(name))
                .findAny();

        return client.orElse(null);
    }

    @Override
    public void saveNewClient(String name, String lastName, Address address) {
        Client client = createClient(name, lastName, address);
        clients.add(client);
    }

    @Override
    public void deleteClient(String name) {
        clients.removeIf(c -> c.getName().equals(name));
    }

    @Override
    public Client updateClient(String name, String newName, String newLastName, Address newAddress) {
        Optional<Client> client = clients.stream()
                .filter(c -> c.getName().equals(name))
                .findAny();

        client.ifPresent(c -> {
            c.setName(newName);
            c.setAddress(newAddress);
            c.setLastName(newLastName);
        });

        return findByName(newName);
    }

    private static Client createClient(String name, String lastName, Address address) {
        return Client.builder()
                .name(name)
                .lastName(lastName)
                .address(address)
                .build();
    }
}