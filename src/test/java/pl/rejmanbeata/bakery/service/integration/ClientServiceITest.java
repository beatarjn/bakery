package pl.rejmanbeata.bakery.service.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.rejmanbeata.bakery.database.AddressEntity;
import pl.rejmanbeata.bakery.database.ClientEntity;
import pl.rejmanbeata.bakery.jpa_repository.ClientRepository;
import pl.rejmanbeata.bakery.service.ClientService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static pl.rejmanbeata.bakery.TestEntitiesFactory.createClientEntity;

@SpringBootTest
class ClientServiceITest {

    public static final String ADAM = "Adam";
    public static final String SMITH = "Smith";
    @Autowired
    private ClientService clientService;
    @Autowired
    private ClientRepository clientRepository;

    @Test
    void testFindClientById_shouldReturnClient() {
        ClientEntity foundClient = clientService.getClientById(1L);

        assertNotNull(foundClient);
    }

    @Test
    void testFindClientById_shouldReturnNull() {
        ClientEntity foundClient = clientService.getClientById(8L);

        assertNull(foundClient);
    }

    @Test
    void testGetAllClients_shouldFindAll() {
        List<ClientEntity> allClients = clientService.getAllClients();

        assertNotNull(allClients);
        assertEquals(4, allClients.size());
    }

    @Test
    void testSave_shouldSaveClient() {
        ClientEntity clientEntity = createClientEntity(ADAM, SMITH, new AddressEntity());

        List<ClientEntity> allClients = clientService.getAllClients();
        assertNotNull(allClients);

        clientRepository.save(clientEntity);

        List<ClientEntity> allClientsAfterSave = clientService.getAllClients();

        assertThat(allClientsAfterSave).hasSize(allClients.size() + 1);
    }

    @Test
    void testDeleteById_shouldDeleteClient() {
        ClientEntity clientEntity = createClientEntity(ADAM, SMITH, new AddressEntity());
        clientService.save(clientEntity);
        List<ClientEntity> allClients = clientService.getAllClients();
        assertNotNull(allClients);

        clientService.deleteClientById(clientEntity.getId());

        List<ClientEntity> allAddressesAfterDelete = clientService.getAllClients();

        assertThat(allAddressesAfterDelete).hasSize(allClients.size() - 1);
        assertNull(clientService.getClientById(clientEntity.getId()));
    }

}