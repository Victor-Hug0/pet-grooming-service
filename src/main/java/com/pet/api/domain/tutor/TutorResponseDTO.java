package com.pet.api.domain.tutor;

public record TutorResponseDTO(Long id, String name, String phoneNumber) {
    public TutorResponseDTO(Long id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
