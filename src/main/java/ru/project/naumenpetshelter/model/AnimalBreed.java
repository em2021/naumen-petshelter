package ru.project.naumenpetshelter.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "animal_breed", schema = "petshelter")
public class AnimalBreed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "animal_type")
    private AnimalType animal_type;
    private String name;
    private String description;
    private OffsetDateTime creation_date;
    private OffsetDateTime last_update;
}