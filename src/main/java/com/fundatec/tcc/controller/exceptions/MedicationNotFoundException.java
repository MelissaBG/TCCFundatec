package com.fundatec.tcc.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MedicationNotFoundException extends Throwable {

    public MedicationNotFoundException(String message) {
        super(message);
    }
}