package com.pet.api.domain.pet;

public record PetPUTRequestDTO(
        String name,
        Integer age,
        String breed,
        Long tutorId) {
}
