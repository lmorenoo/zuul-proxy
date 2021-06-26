package com.test.human.resource.api.exception;

public class CandidateNotFoundException extends NotFoundException {

    public CandidateNotFoundException(Long id) {
        super(String.format("Candidate %s not found", id));
    }

}
