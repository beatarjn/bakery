package pl.rejmanbeata.bakery.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.rejmanbeata.bakery.model.employee.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final List<Employee> employeeList = new ArrayList<>();

    @GetMapping("/{name}")
    public ResponseEntity<Employee> getEmployeeByName(@PathVariable("name") String name) {
        Optional<Employee> employee = employeeList.stream()
                .filter(emp -> emp.getName().equalsIgnoreCase(name))
                .findFirst();

        return employee
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        employeeList.add(employee);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @PutMapping("/{name}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("name") String name, @RequestBody Employee updatedEmployee) {
        Optional<Employee> existingEmployee = employeeList.stream()
                .filter(emp -> emp.getName().equalsIgnoreCase(name))
                .findFirst();

        if (existingEmployee.isPresent()) {
            Employee emp = existingEmployee.get();
            emp.setLastName(updatedEmployee.getLastName());
            emp.setRole(updatedEmployee.getRole());
            return new ResponseEntity<>(emp, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("name") String name) {
        Optional<Employee> existingEmployee = employeeList.stream()
                .filter(emp -> emp.getName().equalsIgnoreCase(name))
                .findFirst();

        if (existingEmployee.isPresent()) {
            employeeList.remove(existingEmployee.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }
}
