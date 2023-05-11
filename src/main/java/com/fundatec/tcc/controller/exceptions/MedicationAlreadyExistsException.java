package com.fundatec.tcc.controller.exceptions;

public class MedicationAlreadyExistsException extends Throwable {
    public MedicationAlreadyExistsException(String message) {
        super(message);
    }
}
