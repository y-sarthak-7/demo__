package net.javaguides.springboot.controller;
import net.javaguides.springboot.model.Test;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    // build create employee REST API
    @PostMapping
    public Employee createEmployee(@RequestParam String proName, @RequestParam String floName, @RequestBody Test test) {

        Employee employee = new Employee(proName, floName, LocalDateTime.parse(test.down_from), LocalDateTime.parse(test.down_to));

//        employee.setProvider_name(proName);
//        employee.setFlow_name(floName);
//        employee.setDowntime_from(test.down_from);
//        employee.setDowntime_to(test.down_to);


        return employeeRepository.save(employee);
    }

    // build get employee by id REST API
    @GetMapping("getbyid")
    public ResponseEntity<Employee> getEmployeeById(@RequestParam String proName){
        Employee employee = employeeRepository.findById(proName)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" + proName));
        return ResponseEntity.ok(employee);
    }

    // build update employee REST API
    @PutMapping("put")
    public ResponseEntity<Employee> updateEmployee(@RequestParam String proName, @RequestParam String floName, @RequestBody Test test) {
        Employee updateEmployee = employeeRepository.findById(proName)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + proName));

//        updateEmployee.setProvider_name(proName);
        updateEmployee.setFlow_name(floName);
        updateEmployee.setDowntime_from(LocalDateTime.parse(test.down_from));
        updateEmployee.setDowntime_to(LocalDateTime.parse(test.down_to));

        employeeRepository.save(updateEmployee);

        return ResponseEntity.ok(updateEmployee);
    }

    // build delete employee REST API
    @DeleteMapping("deletebyid")
    public ResponseEntity<HttpStatus> deleteEmployee(@RequestParam String proName){

        Employee employee = employeeRepository.findById(proName)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + proName));

        employeeRepository.delete(employee);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}