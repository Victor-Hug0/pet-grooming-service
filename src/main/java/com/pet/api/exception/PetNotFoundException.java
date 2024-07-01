package com.pet.api.exception;

public class PetNotFoundException extends RuntimeException{
    public PetNotFoundException(Long id){
        super("Pet not found, id:" + id);
    }
}
