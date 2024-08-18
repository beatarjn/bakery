package pl.rejmanbeata.bakery.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.rejmanbeata.bakery.model.address.Address;
import pl.rejmanbeata.bakery.model.client.Client;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ListBasedClientRepositoryTest {

    private List<Client> list;
    private ListBasedClientRepository repository;

    @BeforeEach
    void setUp() {
        list = initClientList();
        repository = new ListBasedClientRepository(list);
    }

    @Test
    void findAll() {
        List<Client> all = repository.findAll();

        assertThat(all)
                .isNotEmpty()
                .hasSize(3);
    }

    @Test
    void findByName() {
        Client client = repository.findByName("Adam");

        assertNotNull(client);
        assertEquals("Adam", client.getName());
        assertEquals("Test", client.getLastName());
        assertEquals(new Address(5, 65), client.getAddress());
    }

    @Test
    void findByName_NonExistingClient() {
        Client client = repository.findByName("John");

        assertNull(client);
    }

    @Test
    void saveNewClient() {
        assertEquals(3, list.size());

        repository.saveNewClient("Mark", "Thompson", new Address(23, 44));

        assertEquals(4, list.size());
    }

    @Test
    void deleteClient() {
        assertEquals(3, list.size());

        repository.deleteClient("Jerry");

        assertEquals(2, list.size());
    }

    @Test
    void deleteClient_NonExistingClient() {
        assertEquals(3, list.size());

        repository.deleteClient("Temp");

        assertEquals(3, list.size());
    }

    @Test
    void updateClient() {
        Client updatedClient = repository.updateClient("Hanna", "Kate", "Spears", new Address(23, 45));

        assertEquals(3, list.size());
        assertEquals("Kate", updatedClient.getName());
        assertEquals("Spears", updatedClient.getLastName());
        assertEquals(new Address(23, 45), updatedClient.getAddress());

        Client oldClient = repository.findByName("Hanna");

        assertNull(oldClient);
    }

    private List<Client> initClientList() {
        List<Client> clients = new ArrayList<>();
        clients.add(createClient("Hanna", "Blue", new Address(1, 34)));
        clients.add(createClient("Adam", "Test", new Address(5, 65)));
        clients.add(createClient("Jerry", "Morgan", new Address(23, 64)));
        return clients;
    }

    private Client createClient(String name, String lastName, Address address) {
        return Client.builder()
                .name(name)
                .lastName(lastName)
                .address(address)
                .build();
    }
}