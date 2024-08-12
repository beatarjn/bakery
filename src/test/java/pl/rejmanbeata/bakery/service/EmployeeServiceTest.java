package pl.rejmanbeata.bakery.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.rejmanbeata.bakery.database.AddressEntity;
import pl.rejmanbeata.bakery.database.EmployeeEntity;
import pl.rejmanbeata.bakery.jpa_repository.EmployeeRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeService employeeService;
    private EmployeeEntity employee;
    private Random random = new Random();

    @BeforeEach
    public void setup() {
        employee = EmployeeEntity.builder()
                .id(1L)
                .lastName("Doe")
                .address(new AddressEntity(1L, random.nextDouble(), random.nextDouble()))
                .name("John")
                .role("Manager")
                .build();
    }

    @Test
    void testSave_shouldSaveAndReturnEmployee() {
        given(employeeRepository.save(employee)).willReturn(employee);

        EmployeeEntity savedEmployee = employeeService.save(employee);

        assertThat(savedEmployee).isNotNull();
    }

    @Test
    void testGetAllEmployees_shouldReturnAllEmployees() {
        EmployeeEntity employee2 = EmployeeEntity.builder()
                .id(2L)
                .lastName("Smith")
                .address(new AddressEntity(2L, random.nextDouble(), random.nextDouble()))
                .name("Anna")
                .build();

        given(employeeRepository.findAll()).willReturn(List.of(employee, employee2));

        List<EmployeeEntity> employees = employeeService.getAllEmployees();

        assertThat(employees)
                .isNotNull()
                .hasSize(2);
    }

    @Test
    void testGetAllEmployees_shouldReturnEmptyList() {
        given(employeeRepository.findAll()).willReturn(Collections.emptyList());

        List<EmployeeEntity> employees = employeeService.getAllEmployees();

        assertThat(employees).isEmpty();
    }

    @Test
    void testGetEmployeeById_shouldReturnEmployeeById() {
        given(employeeRepository.findById(1L)).willReturn(Optional.of(employee));

        EmployeeEntity savedEmployee = employeeService.getEmployeeById(employee.getId());

        assertThat(savedEmployee).isNotNull();
    }

    @Test
    void testDelete_shouldDeleteEmployeeById() {
        long employeeId = 1L;

        willDoNothing().given(employeeRepository).deleteById(employeeId);

        employeeService.deleteEmployeeById(employeeId);

        verify(employeeRepository, times(1)).deleteById(employeeId);
    }
}