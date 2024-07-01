package com.pet.api.controller;

import com.pet.api.domain.grooming.*;
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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Horário não disponível para agendamento!");
        }

        GroomingResponseDTO groomingResponseDTO = GroomingMapper.toGroomingDTO(grooming);

        return ResponseEntity.status(HttpStatus.CREATED).body(groomingResponseDTO);
    }

    @GetMapping
    public Page<GroomingResponseDTO> getAllGroomings(@RequestParam(defaultValue = "0") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size) {
        return groomingService.findAllGroomings(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroomingResponseDTO> getGroomingById(@PathVariable Long id) {
        Grooming grooming = groomingService.findGroomingById(id);
        GroomingResponseDTO groomingResponseDTO = GroomingMapper.toGroomingDTO(grooming);
        return ResponseEntity.status(HttpStatus.OK).body(groomingResponseDTO);
    }

    @PutMapping("date/{id}")
    public ResponseEntity<?> updateGroomingDate(@PathVariable Long id, @Valid @RequestBody GroomingUpdateDTO groomingUpdateDTO){
        GroomingResponseDTO groomingResponseDTO = groomingService.changeDate(id, groomingUpdateDTO);

        if (groomingResponseDTO == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Horário não disponível para agendamento!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(groomingResponseDTO);
    }

    @PutMapping("status/{id}")
    public ResponseEntity<?> changeStatus(@PathVariable Long id){
        GroomingResponseDTO groomingResponseDTO = groomingService.changeStatus(id);

        if (groomingResponseDTO == null) {
            return ResponseEntity.badRequest().body("Serviço já possui o status CONCLUIDO!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(groomingResponseDTO);
    }
}
