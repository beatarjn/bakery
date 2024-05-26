package pl.rejmanbeata.bakery.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.rejmanbeata.bakery.database.AddressEntity;
import pl.rejmanbeata.bakery.database.ClientEntity;
import pl.rejmanbeata.bakery.jpa_repository.ClientRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;
    @InjectMocks
    private ClientService clientService;
    private ClientEntity client;
    private Random random = new Random();

    @BeforeEach
    public void setup() {
        client = ClientEntity.builder()
                .id(1L)
                .lastName("Doe")
                .address(new AddressEntity(1L, random.nextDouble(), random.nextDouble()))
                .name("John")
                .build();
    }

    @Test
    void testSave_shouldSaveAndReturnClient() {
        given(clientRepository.save(client)).willReturn(client);

        ClientEntity savedClient = clientService.save(client);

        assertThat(savedClient).isNotNull();
    }

    @Test
    void testGetAllClients_shouldReturnAllClients() {
        ClientEntity client2 = ClientEntity.builder()
                .id(2L)
                .lastName("Smith")
                .address(new AddressEntity(2L, random.nextDouble(), random.nextDouble()))
                .name("Anna")
                .build();

        given(clientRepository.findAll()).willReturn(List.of(client, client2));

        List<ClientEntity> clients = clientService.getAllClients();

        assertThat(clients)
                .isNotNull()
                .hasSize(2);
    }

    @Test
    void testGetAllClients_shouldReturnEmptyList() {
        given(clientRepository.findAll()).willReturn(Collections.emptyList());

        List<ClientEntity> clients = clientService.getAllClients();

        assertThat(clients).isEmpty();
    }

    @Test
    void testGetClientById_shouldReturnClientById() {
        given(clientRepository.findById(1L)).willReturn(Optional.of(client));

        ClientEntity savedClient = clientService.getClientById(client.getId());

        assertThat(savedClient).isNotNull();
    }

    @Test
    void testDelete_shouldDeleteClientById() {
        long clientId = 1L;

        willDoNothing().given(clientRepository).deleteById(clientId);

        clientService.deleteClientById(clientId);

        verify(clientRepository, times(1)).deleteById(clientId);
    }

    @Test
    void testFindByLastName_shouldReturnClientByLastName() {
        given(clientRepository.findByLastName("Doe")).willReturn(client);

        ClientEntity savedClient = clientService.findByLastName(client.getLastName());

        assertThat(savedClient).isNotNull();
    }
}