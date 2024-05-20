package com.libraryservice.Library.service.exception;

import lombok.Data;

@Data
public class FieldErrorResource {
    private String field;
    private String error;
}
