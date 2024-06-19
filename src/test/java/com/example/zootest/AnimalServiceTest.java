package com.example.zootest;



import com.example.zootest.model.Animal;
import com.example.zootest.repository.AnimalRepository;
import com.example.zootest.service.AnimalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class AnimalServiceTest {

    @InjectMocks
    private AnimalService animalService;

    @Mock
    private AnimalRepository animalRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindById() {
        Animal animal = new Animal();
        animal.setId(1L);
        animal.setName("Lion");

        when(animalRepository.findById(1L)).thenReturn(Optional.of(animal));

        Optional<Animal> found = animalService.findById(1L);
        assertEquals("Lion", found.get().getName());
    }
}
