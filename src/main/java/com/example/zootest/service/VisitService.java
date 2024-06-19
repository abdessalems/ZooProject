package com.example.zootest.service;

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

    public List<Visit> findAll() {
        return visitRepository.findAll();
    }

    public Optional<Visit> findById(Long id) {
        return visitRepository.findById(id);
    }

    public void save(Visit visit) {
        visitRepository.save(visit);
    }

    public void deleteById(Long id) {
        visitRepository.deleteById(id);
    }
}
