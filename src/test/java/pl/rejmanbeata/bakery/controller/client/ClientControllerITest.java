package pl.rejmanbeata.bakery.controller.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import pl.rejmanbeata.bakery.controller.ClientController;
import pl.rejmanbeata.bakery.database.AddressEntity;
import pl.rejmanbeata.bakery.database.ClientEntity;
import pl.rejmanbeata.bakery.mapper.ClientMapper;
import pl.rejmanbeata.bakery.model.client.Client;
import pl.rejmanbeata.bakery.service.ClientService;

import java.util.Random;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest({ClientController.class, ClientMapper.class})
class ClientControllerITest {
    private static final Random random = new Random();
    public static final String JOHN = "John";
    public static final String SMITH = "Smith";
    public static final String CLIENTS_PATH = "/clients/";
    @MockBean
    private ClientService clientService;
    @MockBean
    private ClientMapper clientMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetClientByName() throws Exception {
        Long clientId = 1L;
        ClientEntity mockClientEntity = new ClientEntity();
        Client mockClient = new Client();

        when(clientService.getClientById(clientId)).thenReturn(mockClient);
        when(clientMapper.clientEntityToClient(mockClientEntity)).thenReturn(mockClient);

        mockMvc.perform(get(CLIENTS_PATH + clientId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(mockClient.getName()))
                .andExpect(jsonPath("$.lastName").value(mockClient.getLastName()));

        verify(clientService, times(1)).getClientById(clientId);
    }

    @Test
    void testCreateClient() throws Exception {
        ClientEntity clientEntity = createClientEntity("Jane", "Doe", createAddressEntity());
        Client client = new Client();

        when(clientMapper.clientToClientEntity(client)).thenReturn(clientEntity);
        when(clientService.save(clientEntity)).thenReturn(client);

        mockMvc.perform(post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(client)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(client.getName()))
                .andExpect(jsonPath("$.lastName").value(client.getLastName()));

        verify(clientMapper, times(1)).clientToClientEntity(client);
        verify(clientService, times(1)).save(clientEntity);
    }

    @Test
    void testUpdateClient() throws Exception {
        Long clientId = 1L;
        ClientEntity updatedClient = createClientEntity(JOHN, SMITH, createAddressEntity());
        Client client = Client.builder()
                .name(JOHN)
                .lastName(SMITH)
                .build();

        when(clientMapper.clientToClientEntity(eq(client))).thenReturn(updatedClient);
        when(clientService.updateClient(clientId, updatedClient)).thenReturn(client);

        mockMvc.perform(put(CLIENTS_PATH + clientId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(client)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(updatedClient.getName()))
                .andExpect(jsonPath("$.lastName").value(updatedClient.getLastName()));

        verify(clientMapper, times(1)).clientToClientEntity(client);
        verify(clientService, times(1)).updateClient(clientId, updatedClient);
    }

    @Test
    void testUpdateClientNotFound() throws Exception {
        Long clientId = 1L;
        ClientEntity updatedClient = createClientEntity(JOHN, SMITH, null);
        Client client = Client.builder()
                .name(JOHN)
                .lastName(SMITH)
                .build();

        when(clientMapper.clientToClientEntity(client)).thenReturn(updatedClient);
        when(clientService.updateClient(clientId, updatedClient)).thenReturn(null);

        mockMvc.perform(put(CLIENTS_PATH + clientId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updatedClient)))
                .andExpect(status().isNotFound());

        verify(clientMapper, times(1)).clientToClientEntity(client);
        verify(clientService, times(1)).updateClient(clientId, updatedClient);
    }

    @Test
    void testDeleteClient() throws Exception {
        Long clientId = 1L;

        when(clientService.getClientById(clientId)).thenReturn(new Client());
        doNothing().when(clientService).deleteClientById(clientId);

        mockMvc.perform(delete(CLIENTS_PATH + clientId))
                .andExpect(status().isNoContent());

        verify(clientService, times(1)).getClientById(clientId);
        verify(clientService, times(1)).deleteClientById(clientId);
    }

    @Test
    void testDeleteClientNotFound() throws Exception {
        Long clientId = 1L;

        when(clientService.getClientById(clientId)).thenReturn(null);

        mockMvc.perform(delete(CLIENTS_PATH + clientId))
                .andExpect(status().isNotFound());

        verify(clientService, times(1)).getClientById(clientId);
        verify(clientService, times(0)).deleteClientById(clientId);
    }


    private static AddressEntity createAddressEntity() {
        return AddressEntity.builder()
                .longitude(random.nextDouble())
                .latitude(random.nextDouble())
                .build();
    }

    private static ClientEntity createClientEntity(String name, String lastName, AddressEntity address) {
        return ClientEntity.builder()
                .name(name)
                .lastName(lastName)
                .address(address)
                .build();
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}