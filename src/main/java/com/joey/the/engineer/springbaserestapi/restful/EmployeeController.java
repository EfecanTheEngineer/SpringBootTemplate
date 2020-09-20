package com.joey.the.engineer.springbaserestapi.restful;


import com.joey.the.engineer.springbaserestapi.restful.exception.EmployeeNotFoundException;
import com.joey.the.engineer.springbaserestapi.restful.model.Employee;
import com.joey.the.engineer.springbaserestapi.restful.repo.EmployeeMongoRepository;
import com.joey.the.engineer.springbaserestapi.restful.resource.EmployeeResourceAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class EmployeeController {

    private final EmployeeMongoRepository repository;
    private final EmployeeResourceAssembler assembler;

    public EmployeeController(EmployeeMongoRepository repository, EmployeeResourceAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    // Aggregate root

    @GetMapping("/employees")
    public CollectionModel<EntityModel<Employee>> all() {
        return assembler.toCollectionModel(repository.findAll());
    }

    @PostMapping("/employees")
    public ResponseEntity<?> newEmployee(@RequestBody Employee newEmployee) throws URISyntaxException {
        EntityModel<Employee> resource = assembler.toModel(repository.save(newEmployee));

        return ResponseEntity
                .created(new URI(resource.getLink(resource.getContent().getId()).get().expand().getHref()))
                .body(resource);
    }

    // Single item

    @GetMapping("/employees/{id}")
    public EntityModel<Employee> one(@PathVariable String id) {

        Employee employee = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        return assembler.toModel(employee);
    }

    @PutMapping("/employees/{id}")
    public Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable String id) {

        return repository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable String id) {
        repository.deleteById(id);
    }
}