package com.joey.the.engineer.springbaserestapi.restful.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * The type Employee.
 */
@Data
public class Employee {

    private @Id String id;
    private String name;
    private String role;

    /**
     * Instantiates a new Employee.
     */
    public Employee() {
    }

    /**
     * Instantiates a new Employee.
     *
     * @param name the name
     * @param role the role
     */
    public Employee(String name, String role) {
        this.name = name;
        this.role = role;
    }
}