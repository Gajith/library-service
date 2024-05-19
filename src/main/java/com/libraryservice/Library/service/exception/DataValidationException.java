package com.libraryservice.Library.service.exception;

import org.springframework.validation.Errors;


public class DataValidationException extends RuntimeException {

    private  Errors errors;
    public DataValidationException(String message) {
        super(message);
    }

    public DataValidationException(Errors errors) {
        this.errors = errors;
    }

    public Errors getErrors() {
        return errors;
    }
}
