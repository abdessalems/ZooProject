package com.example.zootest.controller;

import com.example.zootest.model.Animal;
import com.example.zootest.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/animals")
public class AnimalController {
    @Autowired
    private AnimalService animalService;

    @GetMapping
    public List<Animal> getAllAnimals() {
        return animalService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Animal> getAnimalById(@PathVariable Long id) {
        return animalService.findById(id);
    }

    @PostMapping
    public void addAnimal(@RequestBody Animal animal) {
        animalService.save(animal);
    }

    @PutMapping("/{id}")
    public void updateAnimal(@PathVariable Long id, @RequestBody Animal animal) {
        animal.setId(id);
        animalService.save(animal);
    }

    @DeleteMapping("/{id}")
    public void deleteAnimal(@PathVariable Long id) {
        animalService.deleteById(id);
    }
}
