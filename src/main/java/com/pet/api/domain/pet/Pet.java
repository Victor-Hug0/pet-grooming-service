package com.pet.api.domain.pet;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pet.api.domain.grooming.Grooming;
import com.pet.api.domain.tutor.Tutor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pet")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    private String breed;
    @ManyToOne(fetch = FetchType.EAGER)
    private Tutor tutor;
    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Grooming> groomingServices = new ArrayList<>();
}
