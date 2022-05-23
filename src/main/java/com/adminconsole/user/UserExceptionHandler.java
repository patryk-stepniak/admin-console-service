package com.adminconsole.user;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler({EmptyResultDataAccessException.class, EntityNotFoundException.class})
    public ResponseEntity<String> entityNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler({EntityExistsException.class})
    public ResponseEntity<String> entityAlreadyExists() {
        return ResponseEntity.badRequest().build();
    }

}
