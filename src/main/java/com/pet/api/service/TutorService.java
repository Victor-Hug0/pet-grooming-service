package com.pet.api.service;

import com.pet.api.domain.tutor.TutorPUTRequestDTO;
import com.pet.api.domain.tutor.TutorRequestDTO;
import com.pet.api.domain.tutor.Tutor;
import com.pet.api.exception.TutorNotFoundException;
import com.pet.api.repository.TutorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TutorService {

    @Autowired
    private TutorRepository tutorRepository;

    @Transactional
    public Tutor registerTutor(TutorRequestDTO data){
        if (tutorRepository.findByPhoneNumber(data.phoneNumber()) != null){
            throw new RuntimeException("Telefone já cadastrado!");
        }

        Tutor tutor = new Tutor();
        tutor.setName(data.name());
        tutor.setPhoneNumber(data.phoneNumber());

        return tutorRepository.save(tutor);
    }

    public Page<Tutor> findAllTutors(int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "name");
        return tutorRepository.findAll(pageable);
    }

    public Tutor findTutorById(Long id){
        return tutorRepository.findById(id).orElseThrow(() -> new TutorNotFoundException(id));
    }

    public Tutor updateTutor(Long id, TutorPUTRequestDTO data){
        Optional<Tutor> tutor = tutorRepository.findById(id);

        if (tutor.isPresent()){
            Tutor tutorUpdate = tutor.get();

            if (data.name() != null){
                tutorUpdate.setName(data.name());
            }
            if (data.phoneNumber() != null){
                tutorUpdate.setPhoneNumber(data.phoneNumber());
            }
            return tutorRepository.save(tutorUpdate);
        }
        return null;
    }

    public void deleteTutor(Long id){
        tutorRepository.deleteById(id);
    }
}
