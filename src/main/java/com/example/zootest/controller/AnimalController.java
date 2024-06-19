package com.example.zootest.controller;

import com.example.zootest.model.Animal;
import com.example.zootest.service.AnimalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/animals")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    // Endpoint to get all animals
    @GetMapping
    public List<Animal> getAllAnimals() {
        return animalService.findAll();
    }

    // Endpoint to get a specific animal by ID
    @GetMapping("/{id}")
    public Optional<Animal> getAnimalById(@PathVariable Long id) {
        return animalService.findById(id);
    }

    // Endpoint to add a new animal
    @PostMapping
    public void addAnimal(@Valid @RequestBody Animal animal) {
        animalService.addAnimal(animal);
    }

    // Endpoint to update an existing animal
    @PutMapping("/{id}")
    public void updateAnimal(@PathVariable Long id, @Valid @RequestBody Animal animal) {
        animal.setId(id);
        animalService.updateAnimal(animal);
    }

    // Endpoint to delete an animal by ID
    @DeleteMapping("/{id}")
    public void deleteAnimal(@PathVariable Long id) {
        animalService.deleteAnimal(id);
    }
}
