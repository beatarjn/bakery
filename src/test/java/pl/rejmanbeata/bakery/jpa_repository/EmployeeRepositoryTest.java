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
import static pl.rejmanbeata.bakery.jpa_repository.TestHelper.generateAddressEntity;

@ActiveProfiles("test")
@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Test
    void shouldSaveEmployee() {
        AddressEntity address = generateAddressEntity();
        addressRepository.save(address);

        EmployeeEntity employee = new EmployeeEntity();
        employee.setName("John");
        employee.setLastName("Doe");
        employee.setAddress(address);
        employee.setRole("Manager");

        EmployeeEntity savedEmployee = employeeRepository.save(employee);

        assertThat(savedEmployee.getId()).isNotNull();
        assertThat(savedEmployee.getName()).isEqualTo("John");
        assertThat(savedEmployee.getLastName()).isEqualTo("Doe");
        assertThat(savedEmployee.getAddress()).isEqualTo(address);
        assertThat(savedEmployee.getRole()).isEqualTo("Manager");
    }

    @Test
    void shouldFindEmployeeById() {
        AddressEntity address = generateAddressEntity();
        addressRepository.save(address);

        EmployeeEntity employee = new EmployeeEntity();
        employee.setName("Jane");
        employee.setLastName("Smith");
        employee.setAddress(address);
        employee.setRole("Sale assistant");
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