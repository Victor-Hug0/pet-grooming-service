package com.pet.api.controller;

import com.pet.api.domain.grooming.Grooming;
import com.pet.api.domain.grooming.GroomingMapper;
import com.pet.api.domain.grooming.GroomingRequestDTO;
import com.pet.api.domain.grooming.GroomingResponseDTO;
import com.pet.api.service.GroomingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

        GroomingResponseDTO groomingResponseDTO = GroomingMapper.toGroomingDTO(grooming);

        return ResponseEntity.status(HttpStatus.CREATED).body(groomingResponseDTO);
    }

    @GetMapping
    public Page<GroomingResponseDTO> getAllGroomings(@RequestParam(defaultValue = "0") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size) {
        return groomingService.findAllGroomings(page, size);
    }
}
