package com.pet.api.domain.pet;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PetRequestDTO(
        @NotBlank(message = "O campo nome não pode ser nulo ou vazio.")
        String name,
        @NotNull(message = "O campo idade não pode ser nulo.")
        Integer age,
        @NotBlank(message = "O campo raça não não pode ser nulo ou vazio.")
        String breed,
        @NotNull(message = "O campo tutorId não pode ser nulo.")
        Long tutorId) {
}
