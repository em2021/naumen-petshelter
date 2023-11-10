package ru.project.naumenpetshelter.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "user", schema = "petshelter")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String lastname;
    private LocalDate birthdate;
    @ManyToOne
    @JoinColumn(name = "sex")
    private Sex sex;
    private String phone_number;
    private String email;
    private String username;
    private String about;
    private OffsetDateTime registration_date;
    private OffsetDateTime last_update;
    private OffsetDateTime last_activity;
}