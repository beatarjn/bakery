package pl.rejmanbeata.bakery.service.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import pl.rejmanbeata.bakery.database.AddressEntity;
import pl.rejmanbeata.bakery.database.EmployeeEntity;
import pl.rejmanbeata.bakery.jpa_repository.EmployeeRepository;
import pl.rejmanbeata.bakery.service.EmployeeService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static pl.rejmanbeata.bakery.TestEntitiesFactory.createEmployeeEntityWithRoleAndAddress;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class EmployeeServiceITest {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void testFindEmployeeById_shouldReturnEmployee() {
        EmployeeEntity foundEmployee = employeeService.getEmployeeById(1L);

        assertNotNull(foundEmployee);
    }

    @Test
    void testFindEmployeeById_shouldReturnNull() {
        EmployeeEntity foundEmployee = employeeService.getEmployeeById(8L);

        assertNull(foundEmployee);
    }

    @Test
    void testGetAllEmployees_shouldFindAll() {
        List<EmployeeEntity> allEmployees = employeeService.getAllEmployees();

        assertNotNull(allEmployees);
        assertEquals(2, allEmployees.size());
    }

    @Test
    void testSave_shouldSaveEmployee() {
        EmployeeEntity employeeEntity = createEmployeeEntityWithRoleAndAddress("Manager", new AddressEntity());

        List<EmployeeEntity> allEmployees = employeeService.getAllEmployees();
        assertNotNull(allEmployees);

        employeeRepository.save(employeeEntity);

        List<EmployeeEntity> allEmployeesAfterSave = employeeService.getAllEmployees();

        assertThat(allEmployeesAfterSave).hasSize(allEmployees.size() + 1);
    }

    @Test
    void testDeleteById_shouldDeleteEmployee() {
        EmployeeEntity employeeEntity = createEmployeeEntityWithRoleAndAddress("Manager", new AddressEntity());
        employeeService.save(employeeEntity);
        List<EmployeeEntity> allEmployees = employeeService.getAllEmployees();
        assertNotNull(allEmployees);

        employeeService.deleteEmployeeById(employeeEntity.getId());

        List<EmployeeEntity> allEmployeesAfterDelete = employeeService.getAllEmployees();

        assertThat(allEmployeesAfterDelete).hasSize(allEmployees.size() - 1);
        assertNull(employeeService.getEmployeeById(employeeEntity.getId()));
    }
}
