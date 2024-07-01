package com.pet.api.controller;

import com.pet.api.domain.pet.PetPUTRequestDTO;
import com.pet.api.domain.pet.PetRequestDTO;
import com.pet.api.domain.pet.PetResponseDTO;
import com.pet.api.domain.tutor.TutorResponseDTO;
import com.pet.api.domain.pet.Pet;
import com.pet.api.domain.tutor.Tutor;
import com.pet.api.service.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @PostMapping
    public ResponseEntity<?> addPet(@Valid @RequestBody PetRequestDTO petData) {
        Pet pet = petService.createPet(petData);

        return ResponseEntity.status(HttpStatus.CREATED).body(pet);
    }

    @GetMapping
    public Page<Pet> getAllPets(@RequestParam(defaultValue = "0") Integer page,
                                @RequestParam(defaultValue = "10") Integer size) {
        return petService.findAllPets(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetResponseDTO> getPetById(@PathVariable Long id) {
        Pet pet = petService.findPetById(id);

        Tutor tutor = pet.getTutor();

        TutorResponseDTO tutorDTO = new TutorResponseDTO(
                tutor.getId(),
                tutor.getName(),
                tutor.getPhoneNumber()
        );

        return ResponseEntity.ok(
                new PetResponseDTO(
                        pet.getId(),
                        pet.getName(),
                        pet.getAge(),
                        pet.getBreed(),
                        tutorDTO
                ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePet(@PathVariable Long id, @RequestBody PetPUTRequestDTO petData) {

        Pet pet = petService.updatePet(id, petData);

        if (pet == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        TutorResponseDTO tutorResponseDTO = new TutorResponseDTO(
                pet.getTutor().getId(),
                pet.getTutor().getName(),
                pet.getTutor().getPhoneNumber()
        );

        return ResponseEntity.ok(
                new PetResponseDTO(
                    pet.getId(),
                    pet.getName(),
                    pet.getAge(),
                    pet.getBreed(),
                    tutorResponseDTO
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePet(@PathVariable Long id) {
        Pet pet = petService.findPetById(id);

        if (pet == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        petService.deletePet(id);

        return ResponseEntity.ok().build();
    }
}
