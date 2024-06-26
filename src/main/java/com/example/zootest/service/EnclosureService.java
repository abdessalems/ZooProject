package com.example.zootest.service;

import com.example.zootest.InvalidOperationException;
import com.example.zootest.model.Enclosure;
import com.example.zootest.repository.EnclosureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnclosureService {

    @Autowired
    private EnclosureRepository enclosureRepository;

    // Retrieve all enclosures
    public List<Enclosure> findAll() {
        return enclosureRepository.findAll();
    }

    // Retrieve an enclosure by ID
    public Optional<Enclosure> findById(Long id) {
        return enclosureRepository.findById(id);
    }

    // Add a new enclosure
    public void addEnclosure(Enclosure enclosure) {
        enclosureRepository.save(enclosure);
    }

    // Update an existing enclosure
    public void updateEnclosure(Enclosure enclosure) {
        enclosureRepository.save(enclosure);
    }

    // Delete an enclosure by ID, if no animals are assigned
    public void deleteEnclosure(Long id) {
        Optional<Enclosure> enclosure = enclosureRepository.findById(id);
        if (enclosure.isPresent()) {
            if (!enclosure.get().getAnimalIds().isEmpty()) {
                throw new InvalidOperationException("Enclosure cannot be deleted as it contains animals.");
            }
            enclosureRepository.deleteById(id);
        } else {
            throw new InvalidOperationException("Enclosure not found for id: " + id);
        }
    }

    // Add an animal to an enclosure
    public void addAnimalToEnclosure(Long enclosureId, Long animalId) {
        Optional<Enclosure> enclosure = enclosureRepository.findById(enclosureId);
        if (enclosure.isEmpty()) {
            throw new InvalidOperationException("Enclosure not found for id: " + enclosureId);
        }

        Enclosure updatedEnclosure = enclosure.get();
        updatedEnclosure.getAnimalIds().add(animalId);
        enclosureRepository.save(updatedEnclosure);
    }

    // Remove an animal from an enclosure
    public void removeAnimalFromEnclosure(Long enclosureId, Long animalId) {
        Optional<Enclosure> enclosure = enclosureRepository.findById(enclosureId);
        if (enclosure.isEmpty()) {
            throw new InvalidOperationException("Enclosure not found for id: " + enclosureId);
        }

        Enclosure updatedEnclosure = enclosure.get();
        if (!updatedEnclosure.getAnimalIds().remove(animalId)) {
            throw new InvalidOperationException("Animal " + animalId + " not found in enclosure " + enclosureId);
        }

        enclosureRepository.save(updatedEnclosure);
    }
}
