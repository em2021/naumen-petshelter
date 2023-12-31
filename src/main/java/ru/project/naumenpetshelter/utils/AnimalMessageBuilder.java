package ru.project.naumenpetshelter.utils;

import ru.project.naumenpetshelter.model.Animal;

import java.util.List;

import static ru.project.naumenpetshelter.constants.Constants.*;

public class AnimalMessageBuilder {

    public String buildAnimalsListMessage(List<Animal> animals) {
        StringBuilder sb = new StringBuilder(ALL_ANIMALS_TEXT).append("\n\n");
        animals.forEach(s -> {
            sb.append("name: ").append(s.getName()).append("\n");
            sb.append("id: ").append(s.getId()).append("\n");
            sb.append("type: ").append(s.getType().getName()).append("\n");
            sb.append("sex: ").append(s.getSex().getName()).append("\n");
            sb.append("breed: ").append(s.getBreed().getName()).append("\n\n");
        });
        return sb.toString();
    }

    public String buildAnimalMessage(Animal animal) {
        StringBuilder sb = new StringBuilder();
        sb.append("name: ").append(animal.getName()).append("\n");
        sb.append("id: ").append(animal.getId()).append("\n");
        sb.append("type: ").append(animal.getType().getName()).append("\n");
        sb.append("sex: ").append(animal.getSex().getName()).append("\n");
        sb.append("breed: ").append(animal.getBreed().getName()).append("\n");
        sb.append("color: ").append(animal.getColor().getName()).append("\n");
        sb.append("eye color: ").append(animal.getEye_color().getName()).append("\n");
        sb.append("birth year: ").append(animal.getBirth_year()).append("\n");
        sb.append("weight: ").append(animal.getWeight()).append("\n");
        sb.append("height: ").append(animal.getHeight()).append("\n");
        sb.append("description: ").append(animal.getDescription()).append("\n");
        return sb.toString();
    }
}