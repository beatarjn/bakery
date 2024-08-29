package pl.rejmanbeata.bakery.controller.unit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import pl.rejmanbeata.bakery.controller.ClientController;
import pl.rejmanbeata.bakery.mapper.ClientMapper;
import pl.rejmanbeata.bakery.model.client.Client;
import pl.rejmanbeata.bakery.service.ClientService;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ClientControllerUnitTest {
    private  ClientController clientController;
    @Mock
    private ClientMapper clientMapper;
    @Mock
    private ClientService clientService;
    private AutoCloseable autoCloseable;

    @BeforeEach
    public void setUp(){
        autoCloseable = MockitoAnnotations.openMocks(this);
        clientController = new ClientController(clientMapper, clientService);
    }

    @Test
    void shouldGetClientById(){
        //Given
        when(clientMapper.clientEntityToClient(any())).thenReturn(new Client());

        //When
        ResponseEntity<Client> clientById = clientController.getClientById(1L);

        //Then
        assertNotNull(clientById);
    }

    @AfterEach
    public void tearDown() throws Exception {
        autoCloseable.close();
    }


}
