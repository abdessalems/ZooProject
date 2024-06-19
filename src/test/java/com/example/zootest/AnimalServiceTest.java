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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class AnimalServiceTest {

    @Mock
    private AnimalRepository animalRepository;

    @InjectMocks
    private AnimalService animalService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveAnimal_ThrowsException() {
        Animal animal = new Animal(1L, "Lion", "Panthera leo", 5, 1L);

        when(animalRepository.findById(any())).thenReturn(Optional.of(animal));

        assertThrows(InvalidOperationException.class, () -> {
            animalService.addAnimal(animal);
        });
    }


}
