package com.test.human.resource.api.exception;

public class CandidateAlreadyExistsException extends ObjectAlreadyExistsException {

  public CandidateAlreadyExistsException(Long id) {
    super(String.format("The candidate %s already exists", id));
  }
}
