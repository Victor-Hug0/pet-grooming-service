package com.pet.api.domain.tutor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tutors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tutor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;
}
