package pl.rejmanbeata.bakery.controller.employee;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import pl.rejmanbeata.bakery.controller.EmployeeController;
import pl.rejmanbeata.bakery.model.employee.Employee;
import pl.rejmanbeata.bakery.service.EmployeeService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.*;


class EmployeeControllerTest {
    public static final long EMPLOYEE_ID = 1L;
    private EmployeeController employeeController;
    @Mock
    private EmployeeService employeeService;
    private AutoCloseable autoCloseable;
    private Employee employee;

    @BeforeEach
    public void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        employeeController = new EmployeeController(employeeService);
        employee = new Employee();
    }

    @Test
    void shouldGetEmployeeById_Found() {
        when(employeeService.getEmployeeById(any())).thenReturn(employee);

        ResponseEntity<Employee> employeeById = employeeController.getEmployeeById(EMPLOYEE_ID);

        assertNotNull(employeeById.getBody());
        assertEquals(OK, employeeById.getStatusCode());
    }

    @Test
    void shouldGetEmployeeById_NotFound() {
        ResponseEntity<Employee> employeeById = employeeController.getEmployeeById(EMPLOYEE_ID);

        assertNull(employeeById.getBody());
        assertEquals(NOT_FOUND, employeeById.getStatusCode());
    }

    @Test
    void shouldCreateEmployee() {
        ResponseEntity<Employee> response = employeeController.createEmployee(employee);

        verify(employeeService, times(1)).save(employee);
        assertEquals(CREATED, response.getStatusCode());
    }

    @Test
    void shouldUpdateEmployee_Found() {
        Employee updatedEmployee = new Employee();
        Employee savedEmployee = new Employee();

        when(employeeService.updateEmployee(EMPLOYEE_ID, employee)).thenReturn(savedEmployee);

        ResponseEntity<Employee> response = employeeController.updateEmployee(EMPLOYEE_ID, updatedEmployee);

        verify(employeeService, times(1)).updateEmployee(EMPLOYEE_ID, employee);
        assertEquals(OK, response.getStatusCode());
        assertEquals(savedEmployee, response.getBody());
    }

    @Test
    void shouldUpdateEmployee_NotFound() {
        Employee updatedEmployee = new Employee();

        when(employeeService.updateEmployee(EMPLOYEE_ID, employee)).thenReturn(null);

        ResponseEntity<Employee> response = employeeController.updateEmployee(EMPLOYEE_ID, employee);

        verify(employeeService, times(1)).updateEmployee(EMPLOYEE_ID, updatedEmployee);
        assertEquals(NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void shouldDeleteEmployee_Found() {
        when(employeeService.getEmployeeById(EMPLOYEE_ID)).thenReturn(employee);

        ResponseEntity<Void> response = employeeController.deleteEmployee(EMPLOYEE_ID);

        verify(employeeService, times(1)).getEmployeeById(EMPLOYEE_ID);
        verify(employeeService, times(1)).deleteEmployeeById(EMPLOYEE_ID);
        assertEquals(NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void shouldDeleteEmployee_NotFound() {
        when(employeeService.getEmployeeById(EMPLOYEE_ID)).thenReturn(null);

        ResponseEntity<Void> response = employeeController.deleteEmployee(EMPLOYEE_ID);

        verify(employeeService, times(1)).getEmployeeById(EMPLOYEE_ID);
        verify(employeeService, never()).deleteEmployeeById(EMPLOYEE_ID);
        assertEquals(NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void shouldGetAllEmployees() {
        Employee employee1 = new Employee();
        Employee employee2 = new Employee();
        List<Employee> employees = List.of(employee1, employee2);

        when(employeeService.getAllEmployees()).thenReturn(employees);

        ResponseEntity<List<Employee>> response = employeeController.getAllEmployees();

        verify(employeeService, times(1)).getAllEmployees();
        assertEquals(OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(employees, response.getBody());
    }

    @Test
    void shouldGetAllEmployees_EmptyList() {
        when(employeeService.getAllEmployees()).thenReturn(List.of());

        ResponseEntity<List<Employee>> response = employeeController.getAllEmployees();

        verify(employeeService, times(1)).getAllEmployees();
        assertEquals(OK, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
    }

    @AfterEach
    public void tearDown() throws Exception {
        autoCloseable.close();
    }

}