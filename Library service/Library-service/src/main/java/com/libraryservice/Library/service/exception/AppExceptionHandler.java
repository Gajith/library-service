package com.libraryservice.Library.service.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(ResourceNotFoundException resourceNotFoundException, WebRequest request) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(resourceNotFoundException, resourceNotFoundException.getMessage(), httpHeaders, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(DataValidationException.class)
    public ResponseEntity<Object> handleDataValidException(DataValidationException dataValidationException, WebRequest request) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        if(dataValidationException.getMessage()!=null){
            return handleExceptionInternal(dataValidationException, dataValidationException.getMessage(), httpHeaders, HttpStatus.BAD_REQUEST, request);
        }else{
            return handleExceptionInternal(dataValidationException, dataValidationException.getErrors(), httpHeaders, HttpStatus.BAD_REQUEST, request);

        }
    }
}
