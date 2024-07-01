package com.pet.api.domain.pet;

import com.pet.api.domain.tutor.TutorResponseDTO;

public record PetResponseDTO(
        Long id,
        String name,
        Integer age,
        String breed,
        TutorResponseDTO tutorResponseDTO
) {
    public PetResponseDTO(Long id, String name, Integer age, String breed, TutorResponseDTO tutorResponseDTO) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.tutorResponseDTO = tutorResponseDTO;
    }
}
