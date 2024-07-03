package com.pet.api.service;

import com.pet.api.domain.DTOMapper;
import com.pet.api.domain.grooming.*;
import com.pet.api.domain.pet.Pet;
import com.pet.api.exception.GroomingNotFoundException;
import com.pet.api.repository.GroomingRepository;
import com.pet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
            grooming.setStatus(Status.AGENDADO);

            return groomingRepository.save(grooming);
        }
        return null;
    }

    public boolean isAvailable(LocalDateTime localDateTime) {
        return localDateTime.isAfter(LocalDateTime.now());
    }

    public Page<GroomingResponseDTO> findAllGroomings(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "dateTime");
        Page<Grooming> groomings = groomingRepository.findAll(pageable);
        return groomings.map(DTOMapper::toGroomingDTO);
    }

    public Grooming findGroomingById(Long id) {
        return groomingRepository.findById(id).orElseThrow(() -> new GroomingNotFoundException(id));
    }

    public GroomingResponseDTO changeStatus(Long id) {
        Grooming grooming = findGroomingById(id);

        if (grooming.getStatus().equals(Status.AGENDADO)){
            grooming.setStatus(Status.CONCLUIDO);
            groomingRepository.save(grooming);
            return DTOMapper.toGroomingDTO(grooming);
        }

        return null;
    }

    public GroomingResponseDTO changeDate(Long id, GroomingUpdateDTO groomingUpdateDTO) {
        Grooming grooming = findGroomingById(id);

        if (isAvailable(groomingUpdateDTO.localDateTime())){
            grooming.setDateTime(groomingUpdateDTO.localDateTime());
            groomingRepository.save(grooming);
            return DTOMapper.toGroomingDTO(grooming);
        }

        return null;
    }

    public boolean deleteGrooming(Long id) {
        Grooming grooming = findGroomingById(id);
        if (grooming.getStatus() == Status.AGENDADO){
            groomingRepository.deleteById(id);
            return true;
        }

        return false;
    }
}
