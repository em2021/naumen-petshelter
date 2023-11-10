package ru.project.naumenpetshelter.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@IdClass(AnimalsStatusesLinkId.class)
@Table(name = "animals_statuses_cross_ref", schema = "petshelter")
public class AnimalsStatusesLink {

    @Id
    @ManyToOne
    @JoinColumn(name = "animal")
    private Animal animal;
    @Id
    @ManyToOne
    @JoinColumn(name = "status")
    private AnimalStatus status;
    @Id
    private OffsetDateTime creation_date;
}
