package pl.rejmanbeata.bakery.jpa_repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import pl.rejmanbeata.bakery.database.AddressEntity;
import pl.rejmanbeata.bakery.database.EmployeeEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static pl.rejmanbeata.bakery.TestEntitiesFactory.*;

@ActiveProfiles("test")
@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Test
    void shouldSaveEmployee() {
        AddressEntity address = createAddressEntity();
        addressRepository.save(address);

        EmployeeEntity employee = createEmployeeEntityWithRoleAndAddress("Manager", address);

        EmployeeEntity savedEmployee = employeeRepository.save(employee);

        assertThat(savedEmployee.getId()).isNotNull();
        assertThat(savedEmployee.getName()).isEqualTo("John");
        assertThat(savedEmployee.getLastName()).isEqualTo("Doe");
        assertThat(savedEmployee.getAddress()).isEqualTo(address);
        assertThat(savedEmployee.getRole()).isEqualTo("Manager");
    }

    @Test
    void shouldFindEmployeeById() {
        AddressEntity address = createAddressEntity();
        addressRepository.save(address);

        EmployeeEntity employee = createEmployeeEntityWithRoleAndAddress("Sales assistant", address);
        EmployeeEntity savedEmployee = employeeRepository.save(employee);

        Optional<EmployeeEntity> foundEmployee = employeeRepository.findById(savedEmployee.getId());

        assertThat(foundEmployee)
                .isPresent()
                .containsSame(savedEmployee);
    }

    @Test
    void shouldFindAll() {
        List<EmployeeEntity> allEmployees = employeeRepository.findAll();

        assertNotNull(allEmployees);
        assertEquals(2, allEmployees.size());
    }

}