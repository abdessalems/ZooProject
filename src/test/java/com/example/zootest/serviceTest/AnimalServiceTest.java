package com.example.zootest.serviceTest;

import com.example.zootest.InvalidOperationException;
import com.example.zootest.model.Animal;
import com.example.zootest.model.Enclosure;
import com.example.zootest.model.Species;
import com.example.zootest.repository.AnimalRepository;
import com.example.zootest.service.AnimalService;
import com.example.zootest.service.EnclosureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AnimalServiceTest {

    @Mock
    private AnimalRepository animalRepository;

    @Mock
    private EnclosureService enclosureService;

    @InjectMocks
    private AnimalService animalService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddAnimal() {
        // Mock data
        Animal newAnimal = new Animal(null, "Lion", Species.LION, LocalDate.of(2018, 5, 10), 2L);
        Enclosure mockEnclosure = new Enclosure(2L, "Enclosure 2", "Medium", List.of("Lion", "Tiger"), new ArrayList<>());

        when(enclosureService.findById(2L)).thenReturn(Optional.of(mockEnclosure));
        doAnswer(invocation -> {
            Animal savedAnimal = invocation.getArgument(0);
            savedAnimal.setId(1L); // Simulate saving with ID generated
            mockEnclosure.getAnimalIds().add(savedAnimal.getId());
            return null;
        }).when(animalRepository).save(any(Animal.class));

        // Call service method
        animalService.addAnimal(newAnimal);

        // Verify repository method invocation
        verify(animalRepository, times(1)).save(any(Animal.class));
        assertEquals(1, mockEnclosure.getAnimalIds().size());
        assertEquals(1L, newAnimal.getId()); // Check if ID is set correctly
    }

    @Test
    public void testAddAnimalWithInvalidEnclosure() {
        // Mock data
        Animal newAnimal = new Animal(null, "Elephant", Species.ELEPHANT, LocalDate.of(2019, 8, 25), 1L);

        when(enclosureService.findById(1L)).thenReturn(Optional.empty());

        // Verify exception handling
        assertThrows(InvalidOperationException.class, () -> animalService.addAnimal(newAnimal));
    }

    @Test
    public void testUpdateAnimal() {
        // Mock data
        Animal existingAnimal = new Animal(1L, "Lion", Species.LION, LocalDate.of(2017, 3, 15), 2L);
        Enclosure mockEnclosure = new Enclosure(2L, "Enclosure 2", "Medium", List.of("Lion", "Tiger"), List.of(1L));

        when(animalRepository.findById(1L)).thenReturn(Optional.of(existingAnimal));
        when(enclosureService.findById(2L)).thenReturn(Optional.of(mockEnclosure));
        doAnswer(invocation -> {
            Animal updatedAnimal = invocation.getArgument(0);
            existingAnimal.setName(updatedAnimal.getName());
            existingAnimal.setSpecies(updatedAnimal.getSpecies());
            existingAnimal.setDateDeNaissance(updatedAnimal.getDateDeNaissance());
            existingAnimal.setEnclosureId(updatedAnimal.getEnclosureId());
            return null;
        }).when(animalRepository).save(any(Animal.class));

        // Call service method
        existingAnimal.setName("Updated Lion");
        animalService.updateAnimal(existingAnimal);

        // Verify repository method invocation
        verify(animalRepository, times(1)).save(any(Animal.class));
        assertEquals("Updated Lion", existingAnimal.getName());
    }

    @Test
    public void testUpdateAnimalWithInvalidEnclosure() {
        // Mock data
        Animal existingAnimal = new Animal(1L, "Tiger", Species.TIGER, LocalDate.of(2015, 10, 20), 3L);

        when(animalRepository.findById(1L)).thenReturn(Optional.of(existingAnimal));
        when(enclosureService.findById(3L)).thenReturn(Optional.empty());

        // Verify exception handling
        assertThrows(InvalidOperationException.class, () -> animalService.updateAnimal(existingAnimal));
    }

    @Test
    public void testUpdateAnimalNotFound() {
        // Mock data
        Animal updatedAnimal = new Animal(999L, "Non-existent Animal", Species.GIRAFFE, LocalDate.of(2021, 1, 1), 2L);

        when(animalRepository.findById(999L)).thenReturn(Optional.empty());

        // Verify exception handling
        assertThrows(InvalidOperationException.class, () -> animalService.updateAnimal(updatedAnimal));
    }

    @Test
    public void testDeleteAnimal() {
        // Mock data
        Animal existingAnimal = new Animal(1L, "Tiger", Species.TIGER, LocalDate.of(2019, 3, 15), 2L);

        when(animalRepository.findById(1L)).thenReturn(Optional.of(existingAnimal));

        // Call service method
        animalService.deleteAnimal(1L);

        // Verify repository method invocation
        verify(animalRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteAnimalNotFound() {
        // Mock data
        long nonExistentAnimalId = 999L;

        when(animalRepository.findById(nonExistentAnimalId)).thenReturn(Optional.empty());

        // Verify exception handling
        assertThrows(InvalidOperationException.class, () -> animalService.deleteAnimal(nonExistentAnimalId));
    }

}
