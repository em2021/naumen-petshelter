package ru.project.naumenpetshelter.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "animal", schema = "petshelter")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "type")
    private AnimalType type;
    @ManyToOne
    @JoinColumn(name = "breed")
    private AnimalBreed breed;
    private String name;
    private int birth_year;
    @ManyToOne
    @JoinColumn(name = "sex")
    private Sex sex;
    @ManyToOne
    @JoinColumn(name = "color")
    private Color color;
    @ManyToOne
    @JoinColumn(name = "eye_color")
    private Color eye_color;
    private int height;
    private int weight;
    private String description;
    private String photo_url;
    private OffsetDateTime registration_date;
    private OffsetDateTime last_update;
}