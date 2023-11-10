package ru.project.naumenpetshelter.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "animal_status", schema = "petshelter")
public class AnimalStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private OffsetDateTime creation_date;
    private OffsetDateTime last_update;
}