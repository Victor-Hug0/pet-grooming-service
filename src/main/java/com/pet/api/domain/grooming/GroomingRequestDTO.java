package com.pet.api.domain.grooming;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record GroomingRequestDTO(
        @NotNull(message = "O campo serviceType não pode ser nulo ou vazio.")
        ServiceType serviceType,
        @NotNull(message = "O campo preço não pode ser nulo.")
        BigDecimal price,
        @NotBlank(message = "O campo descrição não pode ser nulo ou vazio.")
        String description,
        @NotNull(message = "O campo localDateTime não pode ser nulo.")
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime localDateTime,
        @NotNull(message = "O campo petId não pode ser nulo.")
        Long petId) {
}
