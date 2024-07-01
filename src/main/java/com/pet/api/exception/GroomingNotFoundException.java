package com.pet.api.exception;

public class GroomingNotFoundException extends RuntimeException{
    public GroomingNotFoundException(Long id) {
        super("Grooming " + id + " not found");
    }
}
