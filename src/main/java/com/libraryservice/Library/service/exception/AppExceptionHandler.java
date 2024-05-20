package com.libraryservice.Library.service.exception;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    public AppExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

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
        }else {
            List<FieldErrorResource> fieldErrorResources = null;
            if (dataValidationException.getErrors() != null) {
                fieldErrorResources = new ArrayList<>();

                List<FieldError> fieldErrors = dataValidationException.getErrors().getFieldErrors();
                for (FieldError fieldError : fieldErrors) {
                    String ErrorMessageField = fieldError.getField().replaceAll("\\[(.*?)\\]", "");
                    String fieldName = fieldError.getField();
                    if (fieldErrorResources.stream().noneMatch(errorResources -> fieldName.contains(errorResources.getField()))) {
                        FieldErrorResource fieldErrorResource = new FieldErrorResource();
                        fieldErrorResource.setField(fieldName);
                        String messageKey = fieldError.getObjectName() + "." + ErrorMessageField + "." + fieldError.getCode();
                        try {
                            fieldErrorResource.setError(messageSource.getMessage(messageKey,null,request.getLocale()));
                        } catch (Exception ex) {
                            fieldErrorResource.setError(String.format("validation key: %s does not have a value in messages.properties", messageKey));
                        }
                        fieldErrorResources.add(fieldErrorResource);
                    }
                }
            }
            return handleExceptionInternal(dataValidationException, fieldErrorResources, httpHeaders, HttpStatus.BAD_REQUEST, request);

        }
    }
}
