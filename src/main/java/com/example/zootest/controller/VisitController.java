package com.example.zootest.controller;

import com.example.zootest.model.Visit;
import com.example.zootest.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/visits")
public class VisitController {
    @Autowired
    private VisitService visitService;

    @GetMapping
    public List<Visit> getAllVisits() {
        return visitService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Visit> getVisitById(@PathVariable Long id) {
        return visitService.findById(id);
    }

    @PostMapping
    public void addVisit(@RequestBody Visit visit) {
        visitService.save(visit);
    }

    @PutMapping("/{id}")
    public void updateVisit(@PathVariable Long id, @RequestBody Visit visit) {
        visit.setId(id);
        visitService.save(visit);
    }

    @DeleteMapping("/{id}")
    public void deleteVisit(@PathVariable Long id) {
        visitService.deleteById(id);
    }
}
