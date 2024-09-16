package pl.rejmanbeata.bakery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.rejmanbeata.bakery.model.employee.Employee;
import pl.rejmanbeata.bakery.service.EmployeeService;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        return employee == null ? new ResponseEntity<>(NOT_FOUND) : new ResponseEntity<>(employee, OK);
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.save(employee);
        return new ResponseEntity<>(savedEmployee, CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        Employee savedEmployee = employeeService.updateEmployee(id, updatedEmployee);
        return savedEmployee != null ? new ResponseEntity<>(savedEmployee, OK) : new ResponseEntity<>(NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        Employee existingEmployee = employeeService.getEmployeeById(id);
        if (existingEmployee != null) {
            employeeService.deleteEmployeeById(id);
        }
        return new ResponseEntity<>(NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getAllEmployees(), OK);
    }
}