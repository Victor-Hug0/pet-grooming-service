package com.pet.api.domain.user;

public record UserLoginRequestDTO(
        String email,
        String password
) {
}
