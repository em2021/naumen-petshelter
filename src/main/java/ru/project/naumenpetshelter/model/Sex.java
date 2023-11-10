package ru.project.naumenpetshelter.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "sex", schema = "petshelter")
public class Sex {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
}