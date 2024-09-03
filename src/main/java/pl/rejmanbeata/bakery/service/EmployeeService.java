package pl.rejmanbeata.bakery.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.rejmanbeata.bakery.database.EmployeeEntity;
import pl.rejmanbeata.bakery.jpa_repository.EmployeeRepository;
import pl.rejmanbeata.bakery.mapper.EmployeeMapper;
import pl.rejmanbeata.bakery.model.employee.Employee;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public Employee save(Employee employee) {
        EmployeeEntity employeeEntity = employeeMapper.employeeToEmployeeEntity(employee);
        EmployeeEntity savedEmployee = employeeRepository.save(employeeEntity);
        return employeeMapper.employeeEntityToEmployee(savedEmployee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::employeeEntityToEmployee)
                .toList();
    }

    public Employee getEmployeeById(Long id) {
        return employeeMapper.employeeEntityToEmployee(employeeRepository.findById(id).orElse(null));
    }

    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }

    public Employee updateEmployee(Long employeeId, Employee employee) {
        Optional<EmployeeEntity> existingEmployee = employeeRepository.findById(employeeId);
        if (existingEmployee.isPresent()) {
            EmployeeEntity employeeEntity = existingEmployee.get();
            employeeEntity.setLastName(employee.getLastName());
            employeeEntity.setName(employee.getName());
            employeeEntity.setRole(employee.getRole());
            EmployeeEntity savedEmployee = employeeRepository.save(employeeEntity);
            return employeeMapper.employeeEntityToEmployee(savedEmployee);
        }
        return null;
    }
}