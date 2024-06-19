package com.example.zootest.repository;


import com.example.zootest.model.Animal;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AnimalRepository {
    private List<Animal> animals = new ArrayList<>();

    public List<Animal> findAll() {
        return animals;
    }

    public Optional<Animal> findById(Long id) {
        return animals.stream().filter(animal -> animal.getId().equals(id)).findFirst();
    }

    public void save(Animal animal) {
        animals.removeIf(a -> a.getId().equals(animal.getId()));
        animals.add(animal);
    }

    public void deleteById(Long id) {
        animals.removeIf(a -> a.getId().equals(id));
    }
}
