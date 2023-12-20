package ru.project.naumenpetshelter.config;

import org.mapdb.DBMaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.abilitybots.api.db.DBContext;
import org.telegram.abilitybots.api.db.MapDBContext;
import ru.project.naumenpetshelter.utils.AnimalMessageBuilder;

@Configuration
public class PetshelterAppConfig {

    @Bean
    public AnimalMessageBuilder animalMessageBuilder() {
        return new AnimalMessageBuilder();
    }

    @Bean
    public DBContext dbContext() {
        return new MapDBContext(DBMaker.memoryDB().make());
    }
}