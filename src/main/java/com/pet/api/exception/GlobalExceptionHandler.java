package com.pet.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(PetNotFoundException.class)
    public ResponseEntity<String> handlePetNotFoundException(PetNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(TutorNotFoundException.class)
    public ResponseEntity<String> handleTutorNotFoundException(TutorNotFoundException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(GroomingNotFoundException.class)
    public ResponseEntity<String> handleGroomingNotFoundException(GroomingNotFoundException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
