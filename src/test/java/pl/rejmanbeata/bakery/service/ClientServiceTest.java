package pl.rejmanbeata.bakery.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.rejmanbeata.bakery.database.AddressEntity;
import pl.rejmanbeata.bakery.database.ClientEntity;
import pl.rejmanbeata.bakery.jpa_repository.ClientRepository;
import pl.rejmanbeata.bakery.mapper.ClientMapper;
import pl.rejmanbeata.bakery.model.client.Client;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;
import static pl.rejmanbeata.bakery.TestEntitiesFactory.createClientEntity;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {
    public static final String DOE = "Doe";
    public static final String JOHN = "John";
    @Mock
    private ClientRepository clientRepository;
    private ClientService clientService;
    private ClientEntity clientEntity;
    private final Random random = new Random();
    @Mock
    private ClientMapper clientMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        clientEntity = ClientEntity.builder()
                .id(1L)
                .lastName(DOE)
                .address(new AddressEntity(1L, random.nextDouble(), random.nextDouble()))
                .name(JOHN)
                .build();
        clientService = new ClientService(clientRepository, clientMapper);
    }

    @Test
    void testSave_shouldSaveAndReturnClient() {
        when(clientMapper.clientEntityToClient(any())).thenReturn(new Client());

        Client savedClient = clientService.save(clientEntity);

        verify(clientRepository, times(1)).save(clientEntity);
        assertThat(savedClient).isNotNull();
    }

    @Test
    void testGetAllClients_shouldReturnAllClients() {
        ClientEntity client2 = createClientEntity("Anna", "Smith", new AddressEntity());

        given(clientRepository.findAll()).willReturn(List.of(clientEntity, client2));

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
        given(clientRepository.findById(1L)).willReturn(Optional.of(clientEntity));

        ClientEntity savedClient = clientService.getClientById(clientEntity.getId());

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
        given(clientRepository.findByLastName(DOE)).willReturn(clientEntity);

        ClientEntity savedClient = clientService.findByLastName(clientEntity.getLastName());

        assertThat(savedClient).isNotNull();
    }
}