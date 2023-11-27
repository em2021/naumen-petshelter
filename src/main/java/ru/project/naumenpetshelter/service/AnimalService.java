package ru.project.naumenpetshelter.service;

import org.springframework.stereotype.Service;
import ru.project.naumenpetshelter.model.Animal;
import ru.project.naumenpetshelter.repository.AnimalCrudRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    AnimalCrudRepository repository;

    public AnimalService(AnimalCrudRepository repository) {
        this.repository = repository;
    }

    public List<Animal> listAllAnimals() {
        return repository.findAll();
    }

    public List<Animal> listAnimalsByType(String animalType) {
        return repository.findAllByType(animalType.substring(0, animalType.length() - 1));
    }

    public Animal getAnimalById(Integer id) {
        System.out.println("Selecting animal by id " + id);
        Optional<Animal> animal = repository.findById(id);
        return animal.orElse(null);
    }
}