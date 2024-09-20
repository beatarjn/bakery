package pl.rejmanbeata.bakery.service.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import pl.rejmanbeata.bakery.database.AddressEntity;
import pl.rejmanbeata.bakery.database.EmployeeEntity;
import pl.rejmanbeata.bakery.jpa_repository.EmployeeRepository;
import pl.rejmanbeata.bakery.model.employee.Employee;
import pl.rejmanbeata.bakery.service.EmployeeService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static pl.rejmanbeata.bakery.TestEntitiesFactory.createEmployeeEntityWithRoleAndAddress;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class EmployeeServiceITest {
    public static final long ID = 1L;
    public static final String MANAGER = "Manager";
    public static final String JOHN = "John";
    public static final String DOE = "Doe";
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void testFindEmployeeById_shouldReturnEmployee() {
        var foundEmployee = employeeService.getEmployeeById(ID);

        assertNotNull(foundEmployee);
    }

    @Test
    void testFindEmployeeById_shouldReturnNull() {
        var foundEmployee = employeeService.getEmployeeById(8L);

        assertNull(foundEmployee);
    }

    @Test
    void testGetAllEmployees_shouldFindAll() {
        var allEmployees = employeeService.getAllEmployees();

        assertNotNull(allEmployees);
        assertEquals(2, allEmployees.size());
    }

    @Test
    void testSave_shouldSaveEmployee() {
        EmployeeEntity employeeEntity = createEmployeeEntityWithRoleAndAddress(MANAGER, new AddressEntity());

        var allEmployees = employeeService.getAllEmployees();
        assertNotNull(allEmployees);

        employeeRepository.save(employeeEntity);

        var allEmployeesAfterSave = employeeService.getAllEmployees();

        assertThat(allEmployeesAfterSave).hasSize(allEmployees.size() + 1);
    }

    @Test
    void testDeleteById_shouldDeleteEmployee() {
        var employee = Employee.builder()
                .lastName(DOE)
                .name(JOHN)
                .role("Manager")
                .build();
        employeeService.save(employee);
        var allEmployees = employeeService.getAllEmployees();
        assertNotNull(allEmployees);

        employeeService.deleteEmployeeById(ID);

        var allEmployeesAfterDelete = employeeService.getAllEmployees();

        assertThat(allEmployeesAfterDelete).hasSize(allEmployees.size() - 1);
        assertNull(employeeService.getEmployeeById(ID));
    }
}