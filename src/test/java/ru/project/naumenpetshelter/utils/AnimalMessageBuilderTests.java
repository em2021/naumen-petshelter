package ru.project.naumenpetshelter.utils;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.project.naumenpetshelter.model.*;

import java.util.List;
import java.util.stream.Stream;

public class AnimalMessageBuilderTests {

    private AnimalMessageBuilder messageBuilder;

    @BeforeAll
    public static void beforeAll() {
        System.out.println("AnimalMessageBuilderTests tests started");
    }

    @BeforeEach
    public void setUp() {
        messageBuilder = new AnimalMessageBuilder();
        System.out.println("AnimalMessageBuilderTests test started");
    }

    @AfterEach
    public void tearDown() {
        System.out.println("AnimalMessageBuilderTests test completed");
    }

    @AfterAll
    public static void AfterAll() {
        System.out.println("AnimalMessageBuilderTests test completed");
    }

    @ParameterizedTest
    @MethodSource("sourceForMessageBuilderTests")
    public void testBuildAnimalsListMessage_whenCalled_stringContainsAllItemsOfTheList(Animal animal) {
        //given:
        Animal animal2 = new Animal();
        AnimalType type = new AnimalType();
        AnimalBreed breed = new AnimalBreed();
        Sex sex = new Sex();
        Color color = new Color();
        animal2.setName("test_animal2");
        type.setName("test_type2");
        breed.setName("test_breed2");
        sex.setName("test_sex2");
        color.setName("test_color2");
        animal2.setType(type);
        animal2.setBreed(breed);
        animal2.setSex(sex);
        animal2.setColor(color);
        animal2.setEye_color(color);
        //when:
        String actual = messageBuilder.buildAnimalsListMessage(List.of(animal, animal2));
        //then:
        Assertions.assertTrue(actual.contains(animal.getName())
                && actual.contains(animal2.getName()));
    }

    @ParameterizedTest
    @MethodSource("sourceForMessageBuilderTests")
    public void testBuildAnimalMessage_whenCalled_returnString(Animal animal) {
        //when:
        String actual = messageBuilder.buildAnimalMessage(animal);
        //then:
        Assertions.assertTrue(actual.contains(animal.getName()));
    }

    public static Stream<Arguments> sourceForMessageBuilderTests() {
        //given:
        Animal animal = new Animal();
        AnimalType type = new AnimalType();
        AnimalBreed breed = new AnimalBreed();
        Sex sex = new Sex();
        Color color = new Color();
        animal.setName("test_animal");
        type.setName("test_type");
        breed.setName("test_breed");
        sex.setName("test_sex");
        color.setName("test_color");
        animal.setType(type);
        animal.setBreed(breed);
        animal.setSex(sex);
        animal.setColor(color);
        animal.setEye_color(color);
        return Stream.of(Arguments.of(animal));
    }
}