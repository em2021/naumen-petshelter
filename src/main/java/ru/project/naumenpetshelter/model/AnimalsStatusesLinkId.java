package ru.project.naumenpetshelter.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.OffsetDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AnimalsStatusesLinkId implements Serializable {

    private Animal animal;
    private AnimalStatus status;
    private OffsetDateTime creation_date;
}
