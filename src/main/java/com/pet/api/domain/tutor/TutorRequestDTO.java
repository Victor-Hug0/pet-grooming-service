package com.pet.api.domain.tutor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record TutorRequestDTO(
        @NotBlank(message = "O nome não pode estar vazio.")
        String name,
        @NotBlank(message = "O número de telefone não pode estar vazio.")
        @Pattern(regexp = "^\\(\\d{2}\\) \\d{5}-\\d{4}$", message = "Formato inválido para número de telefone. Use o formato (99) 99999-9999.")
        String phoneNumber) {
}
