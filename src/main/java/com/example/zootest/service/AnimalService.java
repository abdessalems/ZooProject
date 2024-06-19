package com.example.zootest.service;


import com.example.zootest.model.Animal;
import com.example.zootest.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {
    @Autowired
    private AnimalRepository animalRepository;

    public List<Animal> findAll() {
        return animalRepository.findAll();
    }

    public Optional<Animal> findById(Long id) {
        return animalRepository.findById(id);
    }

    public void save(Animal animal) {
        animalRepository.save(animal);
    }

    public void deleteById(Long id) {
        animalRepository.deleteById(id);
    }
}
