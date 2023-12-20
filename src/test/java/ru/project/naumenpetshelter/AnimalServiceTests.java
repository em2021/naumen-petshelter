package ru.project.naumenpetshelter;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.project.naumenpetshelter.model.Animal;
import ru.project.naumenpetshelter.repository.AnimalCrudRepository;
import ru.project.naumenpetshelter.service.AnimalService;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;

public class AnimalServiceTests {

    @Mock
    AnimalCrudRepository repository;
    @InjectMocks
    AnimalService service;
    private AutoCloseable closeable;

    @BeforeAll
    public static void beforeAll() {
        System.out.println("AnimalServiceTests tests started");
    }

    @BeforeEach
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        System.out.println("AnimalServiceTests test started");
    }

    @AfterEach
    public void tearDown() throws Exception {
        closeable.close();
        System.out.println("AnimalServiceTests test completed");
    }

    @AfterAll
    public static void AfterAll() {
        System.out.println("AnimalServiceTests test completed");
    }

    @Test
    public void testListAllAnimals_whenCalled_callOnceFindAll() {
        //given:
        Mockito.when(repository.findAll()).thenReturn(List.of(new Animal()));
        //when:
        service.listAllAnimals();
        //then:
        Mockito.verify(repository, times(1)).findAll();
    }

    @Test
    public void testListAllAnimals_whenCalled_returnListOfAnimals() {
        //given:
        Mockito.when(repository.findAll()).thenReturn(List.of(new Animal()));
        //when:
        List<Animal> actual = service.listAllAnimals();
        //then:
        Assertions.assertNotNull(actual);
    }

    @Test
    public void testListAnimalsByType_whenCalled_callOnceFindAllByType() {
        //given:
        Mockito.when(repository.findAllByType(anyString())).thenReturn(List.of(new Animal()));
        //when:
        service.listAnimalsByType("test");
        //then:
        Mockito.verify(repository, times(1)).findAllByType(anyString());
    }

    @Test
    public void testListAnimalsByType_whenCalled_returnListOfAnimals() {
        //given:
        Mockito.when(repository.findAllByType(anyString())).thenReturn(List.of(new Animal()));
        //when:
        List<Animal> actual = service.listAnimalsByType("test");
        //then:
        Assertions.assertNotNull(actual);
    }

    @Test
    public void testGetAnimalById_whenCalled_callOnceFindById() {
        //given:
        Mockito.when(repository.findById(anyInt())).thenReturn(Optional.of(new Animal()));
        //when:
        service.getAnimalById(1);
        //then:
        Mockito.verify(repository, times(1)).findById(anyInt());
    }

    @Test
    public void testGetAnimalById_whenCalled_returnAnimal() {
        //given:
        Mockito.when(repository.findById(anyInt())).thenReturn(Optional.of(new Animal()));
        //when:
        Animal actual = service.getAnimalById(1);
        //then:
        Assertions.assertNotNull(actual);
    }
}