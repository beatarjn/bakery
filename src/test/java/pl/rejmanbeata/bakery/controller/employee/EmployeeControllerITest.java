package pl.rejmanbeata.bakery.controller.employee;

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
import pl.rejmanbeata.bakery.controller.EmployeeController;
import pl.rejmanbeata.bakery.model.employee.Employee;
import pl.rejmanbeata.bakery.service.EmployeeService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.rejmanbeata.bakery.controller.TestMapper.asJsonString;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(EmployeeController.class)
class EmployeeControllerITest {
    public static final String JOHN = "John";
    public static final String DOE = "Doe";
    public static final String EMPLOYEES_PATH = "/employees/";
    public static final long ID = 1L;
    @MockBean
    private EmployeeService employeeService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetEmployeeByName() throws Exception {
        var mockEmployee = createTestEmployee();

        when(employeeService.getEmployeeById(ID)).thenReturn(mockEmployee);

        mockMvc.perform(get(EMPLOYEES_PATH + ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(mockEmployee.getName()))
                .andExpect(jsonPath("$.lastName").value(mockEmployee.getLastName()));

        verify(employeeService, times(1)).getEmployeeById(ID);
    }

    @Test
    void testCreateEmployee() throws Exception {
        var employee = createTestEmployee();

        when(employeeService.save(employee)).thenReturn(employee);

        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(employee)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(employee.getName()))
                .andExpect(jsonPath("$.lastName").value(employee.getLastName()));

        verify(employeeService, times(1)).save(employee);
    }

    @Test
    void testUpdateEmployee() throws Exception {
        var employee = createTestEmployee();

        when(employeeService.updateEmployee(ID, employee)).thenReturn(employee);

        mockMvc.perform(put(EMPLOYEES_PATH + ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(employee)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(employee.getName()))
                .andExpect(jsonPath("$.lastName").value(employee.getLastName()));

        verify(employeeService, times(1)).updateEmployee(ID, employee);
    }

    @Test
    void testUpdateEmployeeNotFound() throws Exception {
        var employee = createTestEmployee();

        when(employeeService.updateEmployee(ID, employee)).thenReturn(null);

        mockMvc.perform(put(EMPLOYEES_PATH + ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(employee)))
                .andExpect(status().isNotFound());

        verify(employeeService, times(1)).updateEmployee(ID, employee);
    }

    @Test
    void testDeleteEmployee() throws Exception {
        when(employeeService.getEmployeeById(ID)).thenReturn(new Employee());
        doNothing().when(employeeService).deleteEmployeeById(ID);

        mockMvc.perform(delete(EMPLOYEES_PATH + ID))
                .andExpect(status().isOk());

        verify(employeeService, times(1)).getEmployeeById(ID);
        verify(employeeService, times(1)).deleteEmployeeById(ID);
    }

    @Test
    void testDeleteEmployeeNotFound() throws Exception {
        when(employeeService.getEmployeeById(ID)).thenReturn(null);

        mockMvc.perform(delete(EMPLOYEES_PATH + ID))
                .andExpect(status().isNotFound());

        verify(employeeService, times(1)).getEmployeeById(ID);
        verify(employeeService, times(0)).deleteEmployeeById(ID);
    }

    private static Employee createTestEmployee() {
        return Employee.builder()
                .lastName(DOE)
                .name(JOHN)
                .role("Manager")
                .build();
    }

}