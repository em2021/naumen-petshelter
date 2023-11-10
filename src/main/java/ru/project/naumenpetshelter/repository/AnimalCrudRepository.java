package ru.project.naumenpetshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.project.naumenpetshelter.model.Animal;

import java.util.List;

@Repository
public interface AnimalCrudRepository extends JpaRepository<Animal, Integer> {

    @Query("select a from Animal a join a.type t where t.name = :name")
    List<Animal> findAllByType(@Param("name") String typeName);

    @Query("select a from Animal a where a.id = :id")
    List<Animal> findAnimalById(@Param("id") Integer id);
}