package pl.rejmanbeata.bakery.service;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.rejmanbeata.bakery.database.AddressEntity;
import pl.rejmanbeata.bakery.database.EmployeeEntity;
import pl.rejmanbeata.bakery.jpa_repository.EmployeeRepository;
import pl.rejmanbeata.bakery.mapper.EmployeeMapper;
import pl.rejmanbeata.bakery.model.employee.Employee;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Transactional
class EmployeeServiceTest {
    public static final String JOHN = "John";
    public static final String DOE = "Doe";
    public static final long ID = 1L;
    @Mock
    private EmployeeRepository employeeRepository;
    private EmployeeService employeeService;
    private EmployeeEntity employeeEntity;
    private Employee employee;
    private final Random random = new Random();
    @Mock
    private EmployeeMapper employeeMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        employee = Employee.builder()
                .lastName(DOE)
                .name(JOHN)
                .role("Manager")
                .build();
        employeeService = new EmployeeService(employeeRepository, employeeMapper);
        employeeEntity = EmployeeEntity.builder()
                .lastName(DOE)
                .address(new AddressEntity(ID, random.nextDouble(), random.nextDouble()))
                .name(JOHN)
                .build();
    }

    @Test
    void testSave_shouldSaveAndReturnEmployee() {
        when(employeeMapper.employeeToEmployeeEntity(any())).thenReturn(employeeEntity);
        when(employeeRepository.save(any())).thenReturn(employeeEntity);
        when(employeeMapper.employeeEntityToEmployee(any())).thenReturn(employee);

        var savedEmployee = employeeService.save(employee);

        assertThat(savedEmployee).isNotNull();
        verify(employeeRepository, times(1)).save(employeeEntity);
    }

    @Test
    void testGetAllEmployees_shouldReturnAllEmployees() {
        var id = 2L;
        var employee2 = EmployeeEntity.builder()
                .id(id)
                .lastName("Smith")
                .address(new AddressEntity(id, random.nextDouble(), random.nextDouble()))
                .name("Anna")
                .build();

        when(employeeRepository.findAll()).thenReturn(List.of(employeeEntity, employee2));

        var employees = employeeService.getAllEmployees();

        assertThat(employees)
                .isNotNull()
                .hasSize(2);
    }

    @Test
    void testGetAllEmployees_shouldReturnEmptyList() {
        when(employeeRepository.findAll()).thenReturn(Collections.emptyList());

        var employees = employeeService.getAllEmployees();

        assertThat(employees).isEmpty();
    }

    @Test
    void testGetEmployeeById_shouldReturnEmployeeById() {
        when(employeeMapper.employeeEntityToEmployee(any())).thenReturn(employee);
        when(employeeRepository.findById(any())).thenReturn(Optional.of(employeeEntity));

        var savedEmployee = employeeService.getEmployeeById(employeeEntity.getId());

        assertThat(savedEmployee).isNotNull();
    }

    @Test
    void testDelete_shouldDeleteEmployeeById() {
        willDoNothing().given(employeeRepository).deleteById(ID);

        employeeService.deleteEmployeeById(ID);

        verify(employeeRepository, times(1)).deleteById(ID);
    }
}