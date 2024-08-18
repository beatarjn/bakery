package pl.rejmanbeata.bakery.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.rejmanbeata.bakery.model.address.Address;
import pl.rejmanbeata.bakery.model.client.Client;

import java.util.Random;

@WebMvcTest(ClientController.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ClientControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    private static final Random random = new Random();

    @Test
    void testGetClientByName() throws Exception {
        Address address = createAddress();

        Client client = createClient("John", "Doe", address);

        mockMvc.perform(MockMvcRequestBuilders.post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(client)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.get("/clients/John"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address.longitude").value(address.getLongitude()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address.latitude").value(address.getLatitude()));
    }

    @Test
    void testCreateClient() throws Exception {
        Address address = createAddress();

        Client client = createClient("Jane", "Smith", address);

        mockMvc.perform(MockMvcRequestBuilders.post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(client)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Jane"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Smith"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address.latitude").value(address.getLatitude()));
    }

    @Test
    void testUpdateClient() throws Exception {
        Address address = createAddress();

        Client client = createClient("Clark", "Kent", address);

        mockMvc.perform(MockMvcRequestBuilders.post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(client)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Address updatedAddress = createAddress();

        Client updatedClient = Client.builder()
                .lastName("Wayne")
                .address(updatedAddress)
                .build();

        mockMvc.perform(MockMvcRequestBuilders.put("/clients/Clark")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedClient)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Wayne"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address.latitude").value(updatedAddress.getLatitude()));
    }

    @Test
    void testDeleteClient() throws Exception {
        Address address = createAddress();

        Client client = createClient("Oliver", "Queen", address);

        mockMvc.perform(MockMvcRequestBuilders.post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(client)))
                .andExpect(MockMvcResultMatchers.status().isCreated());


        mockMvc.perform(MockMvcRequestBuilders.delete("/clients/Oliver"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        mockMvc.perform(MockMvcRequestBuilders.get("/clients/Oliver"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testGetAllClients() throws Exception {
        Address address1 = createAddress();
        Client client1 = createClient("Barry", "Allen", address1);

        Address address2 = createAddress();
        Client client2 = createClient("Hall", "Jordan", address2);

        mockMvc.perform(MockMvcRequestBuilders.post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(client1)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(client2)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.get("/clients"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Barry"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Hall"));
    }

    private static Address createAddress() {
        return Address.builder()
                .longitude(random.nextDouble())
                .latitude(random.nextDouble())
                .build();
    }

    private static Client createClient(String name, String lastName, Address address) {
        return Client.builder()
                .name(name)
                .lastName(lastName)
                .address(address)
                .build();
    }
}
