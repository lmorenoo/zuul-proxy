package com.test.human.resource.api.exception;

public class PositionAlreadyExistsException extends ObjectAlreadyExistsException {

  public PositionAlreadyExistsException(String name) {
    super(String.format("The position %s already exists", name));
  }
}
