package pl.rejmanbeata.bakery.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.rejmanbeata.bakery.database.EmployeeEntity;
import pl.rejmanbeata.bakery.jpa_repository.EmployeeRepository;
import pl.rejmanbeata.bakery.mapper.EmployeeMapper;
import pl.rejmanbeata.bakery.model.employee.Employee;

import java.util.List;

import static pl.rejmanbeata.bakery.database.EmployeeEntity.builder;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public Employee save(Employee employee) {
        var employeeEntity = employeeMapper.employeeToEmployeeEntity(employee);
        var savedEmployee = employeeRepository.save(employeeEntity);
        return employeeMapper.employeeEntityToEmployee(savedEmployee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::employeeEntityToEmployee)
                .toList();
    }

    public Employee getEmployeeById(Long id) {
        return employeeMapper.employeeEntityToEmployee(employeeRepository.findById(id).get());
    }

    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }

    public Employee updateEmployee(Long employeeId, Employee employee) {
        return employeeRepository.findById(employeeId)
                .map(e -> builder()
                        .lastName(employee.getLastName())
                        .name(employee.getName())
                        .role(employee.getRole())
                        .build())
                .map(e -> {
                    var savedEmployee = employeeRepository.save(e);
                    return employeeMapper.employeeEntityToEmployee(savedEmployee);
                })
                .orElse(null);
    }

}
