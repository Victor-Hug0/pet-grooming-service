package com.pet.api.service;

import com.pet.api.domain.grooming.Grooming;
import com.pet.api.domain.grooming.GroomingRequestDTO;
import com.pet.api.domain.pet.Pet;
import com.pet.api.repository.GroomingRepository;
import com.pet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class GroomingService {

    @Autowired
    private GroomingRepository groomingRepository;
    @Autowired
    private PetRepository petRepository;

    public Grooming scheduleGrooming(GroomingRequestDTO groomingDTO) {
        if (isAvailable(groomingDTO.localDateTime())){
            Grooming grooming = new Grooming();
            Pet pet = petRepository.getReferenceById(groomingDTO.petId());
            grooming.setServiceType(groomingDTO.serviceType());
            grooming.setPrice(groomingDTO.price());
            grooming.setDescription(groomingDTO.description());
            grooming.setDateTime(groomingDTO.localDateTime());
            grooming.setPet(pet);

            return groomingRepository.save(grooming);
        }
        return null;
    }

    public boolean isAvailable(LocalDateTime localDateTime) {
        return localDateTime.isAfter(LocalDateTime.now());
    }
}