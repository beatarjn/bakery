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
import pl.rejmanbeata.bakery.model.employee.Employee;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetEmployeeByName() throws Exception {
        Employee employee = createEmployee("John", "Doe", "Baker");

        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.get("/employees/John"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.role").value("Baker"));
    }


    @Test
    void testCreateEmployee() throws Exception {
        Employee employee = createEmployee("Jane", "Smith", "Manager");

        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Jane"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Smith"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.role").value("Manager"));
    }

    @Test
    void testUpdateEmployee() throws Exception {
        Employee employee = createEmployee("Alice", "Smith", "Baker");

        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isCreated());

        Employee updatedEmployee = Employee.builder()
                .lastName("Brown")
                .role("Senior Baker")
                .build();

        mockMvc.perform(MockMvcRequestBuilders.put("/employees/Alice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedEmployee)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Brown"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.role").value("Senior Baker"));
    }

    @Test
    void testDeleteEmployee() throws Exception {
        Employee employee = createEmployee("Alice", "White", "Accountant");

        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isCreated());


        mockMvc.perform(MockMvcRequestBuilders.delete("/employees/Alice"))
                .andExpect(status().isNoContent());

        mockMvc.perform(MockMvcRequestBuilders.get("/employees/Alice"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetAllEmployees() throws Exception {
        Employee employee1 = createEmployee("John", "Doe", "Baker");

        Employee employee2 = createEmployee("Jane", "Doe", "Manager");

        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee1)))
                .andExpect(status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee2)))
                .andExpect(status().isCreated());


        mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Jane"));
    }

    private static Employee createEmployee(String name, String lastName, String role) {
        return Employee.builder()
                .name(name)
                .lastName(lastName)
                .role(role)
                .build();
    }

}