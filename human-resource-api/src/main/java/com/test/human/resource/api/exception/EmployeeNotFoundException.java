package com.test.human.resource.api.exception;

public class EmployeeNotFoundException extends NotFoundException {

  public EmployeeNotFoundException(Long id) {
    super(String.format("Employee %s not found", id));
  }

}
