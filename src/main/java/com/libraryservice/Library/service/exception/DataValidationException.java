package com.libraryservice.Library.service.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.validation.Errors;


@Getter
@NoArgsConstructor
public class DataValidationException extends RuntimeException {



    private  Errors errors;
    public DataValidationException(String message) {
        super(message);
    }

    public DataValidationException(Errors errors) {
        this.errors = errors;
    }

}
