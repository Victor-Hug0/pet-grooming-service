package com.pet.api.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRegisterRequestDTO(
        @NotBlank(message = "O campo nome não pode estar vazio.")
        String name,
        @NotBlank(message = "O campo email não pode estar vazio.")
        @Email(message = "Formato incorreto de email.")
        String email,
        @NotBlank(message = "O campo senha não pode estar vazio.")
        @Size(min = 8, max = 16, message = "A senha deve conter entre 8 e 16 caracteres.")
        String password,
        @NotNull(message = "O campo userRole não pode estar nulo ou vazio.")
        UserRole userRole
) {
}
