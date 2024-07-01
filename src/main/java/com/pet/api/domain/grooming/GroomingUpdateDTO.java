package com.pet.api.domain.grooming;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record GroomingUpdateDTO(
        @NotNull(message = "O campo localDateTime não pode ser nulo.")
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime localDateTime
) {
}
