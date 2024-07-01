package com.pet.api.domain.grooming;

import com.pet.api.domain.pet.Pet;
import com.pet.api.domain.pet.PetResponseDTO;
import com.pet.api.domain.tutor.Tutor;
import com.pet.api.domain.tutor.TutorResponseDTO;

public class GroomingMapper {
    public static GroomingResponseDTO toGroomingDTO(Grooming grooming) {
        return new GroomingResponseDTO(
                grooming.getId(),
                grooming.getServiceType(),
                grooming.getPrice(),
                grooming.getDescription(),
                grooming.getStatus(),
                grooming.getDateTime(),
                toPetDTO(grooming.getPet())
        );
    }

    public static PetResponseDTO toPetDTO(Pet pet) {
        return new PetResponseDTO(
                pet.getId(),
                pet.getName(),
                pet.getAge(),
                pet.getBreed(),
                toTutorDTO(pet.getTutor())
        );
    }

    public static TutorResponseDTO toTutorDTO(Tutor tutor) {
        return new TutorResponseDTO(
                tutor.getId(),
                tutor.getName(),
                tutor.getPhoneNumber()
        );
    }
}
