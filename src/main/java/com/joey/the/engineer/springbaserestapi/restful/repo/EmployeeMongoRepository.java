package com.joey.the.engineer.springbaserestapi.restful.repo;

import com.joey.the.engineer.springbaserestapi.restful.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "employee", path = "employee")
public interface EmployeeMongoRepository extends MongoRepository<Employee, String> {

}
