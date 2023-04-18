package com.fundatec.tcc.controller.exceptions;

public class UsernameAlreadyExistsException extends Throwable {
    public UsernameAlreadyExistsException(String message) {
        super(message);
    }
}
