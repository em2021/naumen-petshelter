package ru.project.naumenpetshelter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.project.naumenpetshelter.utils.AnimalMessageBuilder;

@Configuration
public class PetshelterAppConfig {

    @Bean
    public AnimalMessageBuilder animalMessageBuilder() {
        return new AnimalMessageBuilder();
    }
}