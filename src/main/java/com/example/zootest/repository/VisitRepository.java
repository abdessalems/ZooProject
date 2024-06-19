package com.example.zootest.repository;


import com.example.zootest.model.Visit;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class VisitRepository {
    private List<Visit> visits = new ArrayList<>();

    public List<Visit> findAll()



    public Optional<Visit> findById(Long id) {
        return visits.stream().filter(visit -> visit.getId().equals(id)).findFirst();
    }

    public void save(Visit visit) {
        visits.removeIf(v -> v.getId().equals(visit.getId()));
        visits.add(visit);
    }

    public void deleteById(Long id) {
        visits.removeIf(v -> v.getId().equals(id));
    }
}
