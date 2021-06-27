package com.test.human.resource.api.repository;

import com.test.human.resource.api.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  boolean existsByPersonId(Long personId);
}
