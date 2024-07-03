package com.pet.api.service;

import com.pet.api.domain.DTOMapper;
import com.pet.api.domain.pet.PetPUTRequestDTO;
import com.pet.api.domain.pet.PetRequestDTO;
import com.pet.api.domain.pet.Pet;
import com.pet.api.domain.pet.PetResponseDTO;
import com.pet.api.domain.tutor.Tutor;
import com.pet.api.exception.PetNotFoundException;
import com.pet.api.exception.TutorNotFoundException;
import com.pet.api.repository.PetRepository;
import com.pet.api.repository.TutorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;
    @Autowired
    private TutorRepository tutorRepository;

    @Transactional
    public Pet createPet(PetRequestDTO data) {
        Tutor tutor = tutorRepository.findById(data.tutorId()).orElseThrow(() -> new TutorNotFoundException(data.tutorId()));

        Pet pet = new Pet();
        pet.setName(data.name());
        pet.setAge(data.age());
        pet.setBreed(data.breed());
        pet.setTutor(tutor);

        return petRepository.save(pet);
    }

    public Page<PetResponseDTO> findAllPets(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "name");
        Page<Pet> pets = petRepository.findAll(pageable);
        return pets.map(DTOMapper::toPetDTO);
    }

    public Pet updatePet(Long id, PetPUTRequestDTO data) {
        Optional<Pet> petOptional = petRepository.findById(id);

        if (petOptional.isPresent()) {
            Pet petToUpdate = petOptional.get();

            if (data.name() != null) {
                petToUpdate.setName(data.name());
            }
            if (data.age() != null) {
                petToUpdate.setAge(data.age());
            }
            if (data.breed() != null) {
                petToUpdate.setBreed(data.breed());
            }
            if (data.tutorId() != null) {
                Optional<Tutor> tutorOptional = tutorRepository.findById(data.tutorId());
                if (tutorOptional.isPresent()) {
                    petToUpdate.setTutor(tutorOptional.get());
                } else {
                    throw new TutorNotFoundException(data.tutorId());
                }
            }
            return petRepository.save(petToUpdate);
        }
        return null;
    }


    public Pet findPetById(Long id) {
        return petRepository.findById(id).orElseThrow(() -> new PetNotFoundException(id));
    }

    public void deletePet(Long id) {
        petRepository.deleteById(id);
    }
}
