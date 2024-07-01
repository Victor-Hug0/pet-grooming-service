package com.pet.api.controller;

import com.pet.api.domain.tutor.TutorPUTRequestDTO;
import com.pet.api.domain.tutor.TutorRequestDTO;
import com.pet.api.domain.tutor.TutorResponseDTO;
import com.pet.api.domain.tutor.Tutor;
import com.pet.api.service.TutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tutor")
public class TutorController {

    @Autowired
    private TutorService tutorService;

    @PostMapping
    public ResponseEntity<?> createTutor(@Valid @RequestBody TutorRequestDTO data) {
        Tutor tutor = tutorService.registerTutor(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(tutor);
    }

    @GetMapping
    public Page<Tutor> getAllTutors(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size) {
        return tutorService.findAllTutors(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findTutorById(@PathVariable Long id) {
        Tutor tutor = tutorService.findTutorById(id);

        return ResponseEntity.ok(tutor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTutor(@PathVariable Long id, @RequestBody TutorPUTRequestDTO data) {
        Tutor tutor = tutorService.updateTutor(id, data);

        if (tutor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(new TutorResponseDTO(tutor.getId(), tutor.getName(), tutor.getPhoneNumber()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTutor(@PathVariable Long id) {
        tutorService.deleteTutor(id);
        return ResponseEntity.ok().build();
    }
}
