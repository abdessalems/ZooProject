package com.example.zootest.service;

import com.example.zootest.InvalidOperationException;
import com.example.zootest.model.Enclosure;
import com.example.zootest.model.Visit;
import com.example.zootest.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VisitService {

    @Autowired
    private VisitRepository visitRepository;

    @Autowired
    private EnclosureService enclosureService;

    // Retrieve all visits
    public List<Visit> findAll() {
        return visitRepository.findAll();
    }

    // Retrieve a visit by ID
    public Optional<Visit> findById(Long id) {
        return visitRepository.findById(id);
    }

    // Add a new visit
    public void addVisit(Visit visit) {
        validateVisitEnclosures(visit);
        visitRepository.save(visit);
    }

    // Update an existing visit
    public void updateVisit(Visit visit) {
        validateVisitEnclosures(visit);
        visitRepository.save(visit);
    }

    // Delete a visit by ID
    public void deleteVisit(Long id) {
        visitRepository.deleteById(id);
    }

    // Validate if the visit does not exceed 5 enclosures
    private void validateVisitEnclosures(Visit visit) {
        if (visit.getEnclosureIds().size() > 5) {
            throw new InvalidOperationException("A visit cannot include more than 5 enclosures.");
        }
        for (Long enclosureId : visit.getEnclosureIds()) {
            Optional<Enclosure> enclosure = enclosureService.findById(enclosureId);
            if (!enclosure.isPresent()) {
                throw new InvalidOperationException("Enclosure not found for id: " + enclosureId);
            }
        }
    }
}
