package pl.rejmanbeata.bakery.controller.employee;

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
class EmployeeControllerE2ETest {
    public static final String EMPLOYEES_PATH = "/employees";
    public static final String EMPLOYEES_ID_PATH = "/employees/{id}";
    public static final int DB_SIZE = 2;
    public static final long ID = 1L;
    public static final String SMITH = "Smith";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldGetEmployeeById_Found() throws Exception {
        mockMvc.perform(get(EMPLOYEES_ID_PATH, ID)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastName").value(SMITH))
                .andExpect(jsonPath("$.name").value("Adam"));
    }

    @Test
    void shouldGetEmployeeById_NotFound() throws Exception {
        mockMvc.perform(get(EMPLOYEES_ID_PATH, 76L)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateEmployee() throws Exception {
        mockMvc.perform(get(EMPLOYEES_PATH)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(DB_SIZE)));

        var body = """
                {"name":"Harry","lastName":"Potter","role":"Student"}""";

        mockMvc.perform(post(EMPLOYEES_PATH)
                        .contentType(APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Harry"));

        mockMvc.perform(get(EMPLOYEES_PATH)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(DB_SIZE + 1)));
    }

    @Test
    void shouldDeleteEmployee_Found() throws Exception {
        mockMvc.perform(get(EMPLOYEES_PATH)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(DB_SIZE)));

        mockMvc.perform(delete(EMPLOYEES_ID_PATH, ID))
                .andExpect(status().isOk());

        mockMvc.perform(get(EMPLOYEES_PATH)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(DB_SIZE - 1)));
    }

    @Test
    void shouldDeleteEmployee_NotFound() throws Exception {
        mockMvc.perform(delete(EMPLOYEES_ID_PATH, 76L))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldGetAllEmployees() throws Exception {
        mockMvc.perform(get(EMPLOYEES_PATH)
                        .accept(APPLICATION_JSON))
                .andDo(print())

                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(DB_SIZE)))
                .andExpect(jsonPath("$[*].lastName").isArray())
                .andExpect(jsonPath("$[0].lastName").value(SMITH))
                .andExpect(jsonPath("$[1].lastName").value("Jordan"))
                .andExpect(jsonPath("$[*].role").isNotEmpty())
                .andExpect(jsonPath("$[*].name").isNotEmpty());
    }
}
