package com.test.human.resource.api.exception;

public class PositionNotFoundException extends NotFoundException {

    public PositionNotFoundException(String name) {
        super(String.format("Position %s not found", name));
    }

}
