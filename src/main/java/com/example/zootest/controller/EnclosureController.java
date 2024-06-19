package com.example.zootest.controller;

import com.example.zootest.model.Enclosure;
import com.example.zootest.service.EnclosureService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/enclosures")
public class EnclosureController {

    @Autowired
    private EnclosureService enclosureService;

    // Endpoint to get all enclosures
    @GetMapping
    public List<Enclosure> getAllEnclosures() {
        return enclosureService.findAll();
    }

    // Endpoint to get a specific enclosure by ID
    @GetMapping("/{id}")
    public Optional<Enclosure> getEnclosureById(@PathVariable Long id) {
        return enclosureService.findById(id);
    }

    // Endpoint to add a new enclosure
    @PostMapping
    public void addEnclosure(@Valid @RequestBody Enclosure enclosure) {
        enclosureService.addEnclosure(enclosure);
    }

    // Endpoint to update an existing enclosure
    @PutMapping("/{id}")
    public void updateEnclosure(@PathVariable Long id, @Valid @RequestBody Enclosure enclosure) {
        enclosure.setId(id);
        enclosureService.updateEnclosure(enclosure);
    }

    // Endpoint to delete an enclosure by ID
    @DeleteMapping("/{id}")
    public void deleteEnclosure(@PathVariable Long id) {
        enclosureService.deleteEnclosure(id);
    }
}
