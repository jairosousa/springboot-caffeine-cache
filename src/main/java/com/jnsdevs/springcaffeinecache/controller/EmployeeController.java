package com.jnsdevs.springcaffeinecache.controller;

import com.jnsdevs.springcaffeinecache.model.Employee;
import com.jnsdevs.springcaffeinecache.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @Autor Jairo Nascimento
 * @Created 24/07/2024 - 13:49
 */
@RestController
@RequestMapping("/employees")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping
    public ResponseEntity<Employee> save(@RequestBody Employee employee){
        return new ResponseEntity<>(employeeRepository.save(employee), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Employee>> find(@PathVariable(value = "id") Integer id){
        log.info("Employee data fetched from database:: "+id);
        return ResponseEntity.ok(employeeRepository.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Integer id,
                                                   @RequestBody Employee employeeDetails) {
        var employee = employeeRepository.findById(id).orElseThrow();
        employee.setName(employeeDetails.getName());
        final Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable(value = "id") Integer id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        employee.ifPresent(value -> employeeRepository.delete(value));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
