package com.pet.api.repository;

import com.pet.api.domain.tutor.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorRepository extends JpaRepository<Tutor, Long> {
    Tutor findByPhoneNumber(String phoneNumber);
}
