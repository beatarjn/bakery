package pl.rejmanbeata.bakery.controller.client;

import jakarta.transaction.Transactional;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class ClientControllerE2ETest {
    public static final String CLIENTS_PATH = "/clients";
    public static final String CLIENTS_ID_PATH = "/clients/{id}";
    public static final int DB_SIZE = 3;
    public static final long ID = 1L;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldGetClientById_Found() throws Exception {
        mockMvc.perform(get(CLIENTS_ID_PATH, ID)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.name").value("John"));
    }

    @Test
    void shouldGetClientById_NotFound() throws Exception {
        mockMvc.perform(get(CLIENTS_ID_PATH, 76L)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateClient() throws Exception {
        mockMvc.perform(get(CLIENTS_PATH)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(DB_SIZE)));

        String body = """
                {"name":"Harry","lastName":"Potter","address":{"longitude":12.4924,"latitude":41.8902}}""";

        mockMvc.perform(post(CLIENTS_PATH)
                        .contentType(APPLICATION_JSON)
                        .content(body))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Harry"));

        mockMvc.perform(get(CLIENTS_PATH)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(DB_SIZE + 1)));
    }

    @Test
    void shouldDeleteClient_Found() throws Exception {
        mockMvc.perform(get(CLIENTS_PATH)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(DB_SIZE)));

        mockMvc.perform(delete(CLIENTS_ID_PATH, ID))
                .andExpect(status().isNoContent());

        mockMvc.perform(get(CLIENTS_PATH)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(DB_SIZE - 1)));
    }

    @Test
    void shouldDeleteClient_NotFound() throws Exception {
        mockMvc.perform(delete(CLIENTS_ID_PATH, 76L))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldGetAllClients() throws Exception {
        mockMvc.perform(get(CLIENTS_PATH)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(DB_SIZE)))
                .andExpect(jsonPath("$[*].lastName").isArray())
                .andExpect(jsonPath("$[0].lastName").value("Doe"))
                .andExpect(jsonPath("$[1].lastName").value("Smith"))
                .andExpect(jsonPath("$[2].lastName").value("Johnson"))
                .andExpect(jsonPath("$[*].address").isNotEmpty())
                .andExpect(jsonPath("$[*].name").isNotEmpty());
    }
}