package pl.rejmanbeata.bakery.jpa_repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import pl.rejmanbeata.bakery.database.AddressEntity;
import pl.rejmanbeata.bakery.database.ClientEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static pl.rejmanbeata.bakery.TestEntitiesFactory.createAddressEntity;
import static pl.rejmanbeata.bakery.TestEntitiesFactory.createClientEntity;

@ActiveProfiles("test")
@DataJpaTest
class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Test
    void shouldSaveClient() {
        AddressEntity address = createAddressEntity();
        addressRepository.save(address);

        ClientEntity client = createClientEntity("John", "Doe", address);

        ClientEntity savedClient = clientRepository.save(client);

        assertThat(savedClient.getId()).isNotNull();
        assertThat(savedClient.getName()).isEqualTo("John");
        assertThat(savedClient.getLastName()).isEqualTo("Doe");
        assertThat(savedClient.getAddress()).isEqualTo(address);
    }

    @Test
    void shouldFindClientById() {
        AddressEntity address = createAddressEntity();
        addressRepository.save(address);

        ClientEntity client = createClientEntity("Alice", "Smith", address);

        ClientEntity savedClient = clientRepository.save(client);

        Optional<ClientEntity> foundClient = clientRepository.findById(savedClient.getId());

        assertThat(foundClient)
                .isPresent()
                .containsSame(savedClient);
    }

    @Test
    void shouldFindClientByLastName() {
        ClientEntity foundClients = clientRepository.findByLastName("Doe");

        assertNotNull(foundClients);
    }

    @Test
    void shouldDeleteClientById() {
        AddressEntity address = createAddressEntity();
        addressRepository.save(address);

        ClientEntity client = createClientEntity("John", "Doe", address);

        ClientEntity savedClient = clientRepository.save(client);

        clientRepository.deleteById(savedClient.getId());

        assertThat(clientRepository.findById(savedClient.getId())).isEmpty();
    }

    @Test
    void shouldFindAllClients() {
        List<ClientEntity> allClients = clientRepository.findAll();

        assertThat(allClients).hasSize(3);
    }

}