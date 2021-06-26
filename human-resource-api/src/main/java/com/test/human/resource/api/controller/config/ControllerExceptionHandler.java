package com.test.human.resource.api.controller.config;

import com.test.human.resource.api.exception.*;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ErrorMessage> handle(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(new ErrorMessage(ex.getMessage()));
    }

    @ExceptionHandler({
            PositionAlreadyExistsException.class,
            CandidateAlreadyExistsException.class,
            EmployeeAlreadyExistsException.class
    })
    protected ResponseEntity<ErrorMessage> handle(ObjectAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorMessage(ex.getMessage()));
    }

    @ExceptionHandler({
            PositionNotFoundException.class,
            CandidateNotFoundException.class,
            EmployeeNotFoundException.class
    })
    protected ResponseEntity<ErrorMessage> handle(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(ex.getMessage()));
    }
}
