package com.example.zootest.service;

import com.example.zootest.InvalidOperationException;
import com.example.zootest.model.Animal;
import com.example.zootest.model.Enclosure;
import com.example.zootest.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private EnclosureService enclosureService;

    // Retrieve all animals
    public List<Animal> findAll() {
        return animalRepository.findAll();
    }

    // Retrieve an animal by ID
    public Optional<Animal> findById(Long id) {
        return animalRepository.findById(id);
    }

    // Add a new animal
    public void addAnimal(Animal animal) {
        validateAnimalEnclosure(animal);
        animalRepository.save(animal);
    }

    // Update an existing animal
    public void updateAnimal(Animal animal) {
        validateAnimalEnclosure(animal);
        animalRepository.save(animal);
    }

    // Delete an animal by ID
    public void deleteAnimal(Long id) {
        animalRepository.findById(id).ifPresentOrElse(
                animal -> animalRepository.deleteById(id),
                () -> {
                    throw new InvalidOperationException("Animal not found for id: " + id);
                }
        );
    }

    // Validate if the animal can be assigned to the specified enclosure
    private void validateAnimalEnclosure(Animal animal) {
        Long enclosureId = animal.getEnclosureId();
        if (enclosureId == null) {
            throw new InvalidOperationException("Enclosure ID is required for adding/updating an animal.");
        }

        Optional<Enclosure> enclosure = enclosureService.findById(enclosureId);
        if (enclosure.isEmpty()) {
            throw new InvalidOperationException("Enclosure not found for id: " + enclosureId);
        }

        if (!enclosure.get().getAllowedSpecies().contains(animal.getSpecies())) {
            throw new InvalidOperationException("Species " + animal.getSpecies() + " not allowed in enclosure " + enclosure.get().getName());
        }
    }
}
