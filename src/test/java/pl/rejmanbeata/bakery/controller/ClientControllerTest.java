package pl.rejmanbeata.bakery.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import pl.rejmanbeata.bakery.model.address.Address;
import pl.rejmanbeata.bakery.model.client.Client;

import java.util.Random;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class ClientControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    private static final Random random = new Random();

    @Test
    void testGetClientByName() throws Exception {
        var address = createAddress();

        mockMvc.perform(get("/clients/John"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.address.longitude").value(address.getLongitude()))
                .andExpect(jsonPath("$.address.latitude").value(address.getLatitude()));
    }

    @Test
    void testCreateClient() throws Exception {
        var address = createAddress();

        var client = createClient("Jane", "Smith", address);

        mockMvc.perform(post("/clients")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(client)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Jane"))
                .andExpect(jsonPath("$.lastName").value("Smith"))
                .andExpect(jsonPath("$.address.latitude").value(address.getLatitude()));
    }

    @Test
    void testUpdateClient() throws Exception {
        var address = createAddress();

        var client = createClient("Clark", "Kent", address);

        mockMvc.perform(post("/clients")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(client)))
                .andExpect(status().isCreated());

        Address updatedAddress = createAddress();

        Client updatedClient = Client.builder()
                .lastName("Wayne")
                .address(updatedAddress)
                .build();

        mockMvc.perform(put("/clients/Clark")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedClient)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastName").value("Wayne"))
                .andExpect(jsonPath("$.address.latitude").value(updatedAddress.getLatitude()));
    }

    @Test
    void testDeleteClient() throws Exception {
        var address = createAddress();

        var client = createClient("Oliver", "Queen", address);

        mockMvc.perform(post("/clients")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(client)))
                .andExpect(status().isCreated());

        mockMvc.perform(delete("/clients/Oliver"))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/clients/Oliver"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetAllClients() throws Exception {
        var address1 = createAddress();
        var client1 = createClient("Barry", "Allen", address1);

        var address2 = createAddress();
        var client2 = createClient("Hall", "Jordan", address2);

        mockMvc.perform(post("/clients")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(client1)))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/clients")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(client2)))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/clients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Barry"))
                .andExpect(jsonPath("$[1].name").value("Hall"));
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
