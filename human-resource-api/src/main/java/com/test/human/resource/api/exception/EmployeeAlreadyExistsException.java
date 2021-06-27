package com.test.human.resource.api.exception;

public class EmployeeAlreadyExistsException extends ObjectAlreadyExistsException {

  public EmployeeAlreadyExistsException(Long id) {
    super(String.format("The employee with candidate id %s already exists", id));
  }
}
