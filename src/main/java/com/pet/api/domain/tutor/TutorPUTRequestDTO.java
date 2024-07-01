package com.pet.api.domain.tutor;

import jakarta.validation.constraints.Pattern;

public record TutorPUTRequestDTO(
        String name,
        @Pattern(regexp = "^\\(\\d{2}\\) \\d{5}-\\d{4}$", message = "Formato inválido para número de telefone. Use o formato (99) 99999-9999.")
        String phoneNumber) {
}
