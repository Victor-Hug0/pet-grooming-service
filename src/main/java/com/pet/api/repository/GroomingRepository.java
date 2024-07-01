package com.pet.api.repository;

import com.pet.api.domain.grooming.Grooming;
import org.springframework.data.jpa.repository.JpaRepository;



public interface GroomingRepository extends JpaRepository<Grooming, Long> {

}
