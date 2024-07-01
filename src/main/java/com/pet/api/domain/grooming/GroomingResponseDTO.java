package com.pet.api.domain.grooming;

import com.pet.api.domain.pet.PetResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record GroomingResponseDTO(Long id, ServiceType serviceType, BigDecimal price, String description, Status status, LocalDateTime localDateTime, PetResponseDTO petResponseDTO) {
}
