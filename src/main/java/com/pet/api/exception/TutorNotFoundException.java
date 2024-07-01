package com.pet.api.exception;

public class TutorNotFoundException extends RuntimeException{
    public TutorNotFoundException(Long id) {
        super("Tutor not found, id:" + id);
    }
}
