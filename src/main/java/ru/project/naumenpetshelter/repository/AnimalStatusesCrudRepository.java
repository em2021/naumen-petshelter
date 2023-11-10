package ru.project.naumenpetshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.project.naumenpetshelter.model.AnimalsStatusesLink;

@Repository
public interface AnimalStatusesCrudRepository extends JpaRepository<AnimalsStatusesLink, AnimalsStatusesLink> {
}
