package com.example.zootest.controller;

import com.example.zootest.model.Visit;
import com.example.zootest.service.VisitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/visits")
public class VisitController {

    @Autowired
    private VisitService visitService;

    // Endpoint to get all visits
    @GetMapping
    public List<Visit> getAllVisits() {
        return visitService.findAll();
    }

    // Endpoint to get a specific visit by ID
    @GetMapping("/{id}")
    public Optional<Visit> getVisitById(@PathVariable Long id) {
        return visitService.findById(id);
    }

    // Endpoint to add a new visit
    @PostMapping
    public void addVisit(@Valid @RequestBody Visit visit) {
        visitService.addVisit(visit);
    }

    // Endpoint to update an existing visit
    @PutMapping("/{id}")
    public void updateVisit(@PathVariable Long id, @Valid @RequestBody Visit visit) {
        visit.setId(id);
        visitService.updateVisit(visit);
    }

    // Endpoint to delete a visit by ID
    @DeleteMapping("/{id}")
    public void deleteVisit(@PathVariable Long id) {
        visitService.deleteVisit(id);
    }
}
