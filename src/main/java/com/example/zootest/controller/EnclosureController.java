package com.example.zootest.controller;

import com.example.zootest.model.Enclosure;
import com.example.zootest.service.EnclosureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/enclosures")
public class EnclosureController {
    @Autowired
    private EnclosureService enclosureService;

    @GetMapping
    public List<Enclosure> getAllEnclosures() {
        return enclosureService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Enclosure> getEnclosureById(@PathVariable Long id) {
        return enclosureService.findById(id);
    }

    @PostMapping
    public void addEnclosure(@RequestBody Enclosure enclosure) {
        enclosureService.save(enclosure);
    }

    @PutMapping("/{id}")
    public void updateEnclosure(@PathVariable Long id, @RequestBody Enclosure enclosure) {
        enclosure.setId(id);
        enclosureService.save(enclosure);
    }

    @DeleteMapping("/{id}")
    public void deleteEnclosure(@PathVariable Long id) {
        enclosureService.deleteById(id);
    }
}
