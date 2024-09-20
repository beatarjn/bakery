package pl.rejmanbeata.bakery.controller.client;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import pl.rejmanbeata.bakery.controller.ClientController;
import pl.rejmanbeata.bakery.database.ClientEntity;
import pl.rejmanbeata.bakery.mapper.ClientMapper;
import pl.rejmanbeata.bakery.model.client.Client;
import pl.rejmanbeata.bakery.service.ClientService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.*;

class ClientControllerTest {
    public static final long CLIENT_ID = 1L;
    private ClientController clientController;
    @Mock
    private ClientMapper clientMapper;
    @Mock
    private ClientService clientService;
    private AutoCloseable autoCloseable;
    private ClientEntity clientEntity;
    private Client client;

    @BeforeEach
    public void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        clientController = new ClientController(clientMapper, clientService);
        clientEntity = new ClientEntity();
        client = new Client();
    }

    @Test
    void shouldGetClientById_Found() {
        when(clientMapper.clientEntityToClient(any())).thenReturn(client);
        when(clientService.getClientById(any())).thenReturn(client);

        ResponseEntity<Client> clientById = clientController.getClientById(CLIENT_ID);

        assertNotNull(clientById.getBody());
        assertEquals(OK, clientById.getStatusCode());
    }

    @Test
    void shouldGetClientById_NotFound() {
        when(clientMapper.clientEntityToClient(any())).thenReturn(null);

        ResponseEntity<Client> clientById = clientController.getClientById(CLIENT_ID);

        assertNull(clientById.getBody());
        assertEquals(NOT_FOUND, clientById.getStatusCode());
    }

    @Test
    void shouldCreateClient() {
        when(clientMapper.clientToClientEntity(any())).thenReturn(clientEntity);

        ResponseEntity<Client> response = clientController.createClient(client);

        verify(clientMapper, times(1)).clientToClientEntity(client);
        verify(clientService, times(1)).save(clientEntity);
        assertEquals(CREATED, response.getStatusCode());
        assertEquals(client, response.getBody());
    }

    @Test
    void shouldUpdateClient_Found() {
        Client updatedClient = new Client();
        Client savedClient = new Client();

        when(clientMapper.clientToClientEntity(updatedClient)).thenReturn(clientEntity);
        when(clientService.updateClient(CLIENT_ID, clientEntity)).thenReturn(savedClient);

        ResponseEntity<Client> response = clientController.updateClient(CLIENT_ID, updatedClient);

        verify(clientMapper, times(1)).clientToClientEntity(updatedClient);
        verify(clientService, times(1)).updateClient(CLIENT_ID, clientEntity);
        assertEquals(OK, response.getStatusCode());
        assertEquals(savedClient, response.getBody());
    }

    @Test
    void shouldUpdateClient_NotFound() {
        Client updatedClient = new Client();

        when(clientMapper.clientToClientEntity(updatedClient)).thenReturn(clientEntity);
        when(clientService.updateClient(CLIENT_ID, clientEntity)).thenReturn(null);

        ResponseEntity<Client> response = clientController.updateClient(CLIENT_ID, updatedClient);

        verify(clientMapper, times(1)).clientToClientEntity(updatedClient);
        verify(clientService, times(1)).updateClient(CLIENT_ID, clientEntity);
        assertEquals(NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void shouldDeleteClient_Found() {
        when(clientService.getClientById(CLIENT_ID)).thenReturn(client);

        ResponseEntity<Void> response = clientController.deleteClient(CLIENT_ID);

        verify(clientService, times(1)).getClientById(CLIENT_ID);
        verify(clientService, times(1)).deleteClientById(CLIENT_ID);
        assertEquals(NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void shouldDeleteClient_NotFound() {
        when(clientService.getClientById(CLIENT_ID)).thenReturn(null);

        ResponseEntity<Void> response = clientController.deleteClient(CLIENT_ID);

        verify(clientService, times(1)).getClientById(CLIENT_ID);
        verify(clientService, never()).deleteClientById(CLIENT_ID);
        assertEquals(NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void shouldGetAllClients() {
        Client clientEntity1 = new Client();
        Client clientEntity2 = new Client();
        List<Client> clients = List.of(clientEntity1, clientEntity2);

        when(clientService.getAllClients()).thenReturn(clients);

        ResponseEntity<List<Client>> response = clientController.getAllClients();

        verify(clientService, times(1)).getAllClients();
        assertEquals(OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(clients, response.getBody());
    }

    @Test
    void shouldGetAllClients_EmptyList() {
        when(clientService.getAllClients()).thenReturn(List.of());

        ResponseEntity<List<Client>> response = clientController.getAllClients();

        verify(clientService, times(1)).getAllClients();
        verify(clientMapper, never()).clientEntityToClient(any());
        assertEquals(OK, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
    }

    @AfterEach
    public void tearDown() throws Exception {
        autoCloseable.close();
    }

}