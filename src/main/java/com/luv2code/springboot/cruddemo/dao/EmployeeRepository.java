package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // that's it ... no need to write any code LOL!

    // add a method to sort by lastname
    public List<Employee> findAllByOrderByLastNameAsc();
    // Spring Data JPA will parse the method name
    // Looks for a specific format and pattern
    // Creates appropriate query behind the scenes



}
