package com.pet.api.controller;

import com.pet.api.domain.grooming.Grooming;
import com.pet.api.domain.grooming.GroomingRequestDTO;
import com.pet.api.domain.grooming.GroomingResponseDTO;
import com.pet.api.domain.pet.Pet;
import com.pet.api.domain.pet.PetResponseDTO;
import com.pet.api.domain.tutor.TutorResponseDTO;
import com.pet.api.service.GroomingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/grooming")
public class GroomingController {

    @Autowired
    private GroomingService groomingService;

    @PostMapping
    public ResponseEntity<?> registerGrooming(@RequestBody @Valid GroomingRequestDTO groomingData) {
        Grooming grooming = groomingService.scheduleGrooming(groomingData);
        

        if (grooming == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Horário não disponível para agendamento!");
        }

        PetResponseDTO petResponseDTO = getPetResponseDTO(grooming);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new GroomingResponseDTO(
                        grooming.getId(),
                        grooming.getServiceType(),
                        grooming.getPrice(),
                        grooming.getDescription(),
                        grooming.getDateTime(),
                        petResponseDTO
                )
        );
    }

    private PetResponseDTO getPetResponseDTO(Grooming grooming) {
        TutorResponseDTO tutorResponseDTO = new TutorResponseDTO(
                grooming.getPet().getTutor().getId(),
                grooming.getPet().getTutor().getName(),
                grooming.getPet().getTutor().getPhoneNumber());

        return new PetResponseDTO(
                grooming.getPet().getId(),
                grooming.getPet().getName(),
                grooming.getPet().getAge(),
                grooming.getPet().getBreed(),
                tutorResponseDTO
        );
    }
}
