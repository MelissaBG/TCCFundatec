package com.fundatec.tcc.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class MedicationAlreadyExistsException extends Throwable {
    public MedicationAlreadyExistsException(String message) {
        super(message);
    }
}
