package com.example.zootest.repository;


import com.example.zootest.model.Enclosure;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EnclosureRepository {
    private List<Enclosure> enclosures = new ArrayList<>();

    public List<Enclosure> findAll() {
        return enclosures;
    }

    public Optional<Enclosure> findById(Long id) {
        return enclosures.stream().filter(enclosure -> enclosure.getId().equals(id)).findFirst();
    }

    public void save(Enclosure enclosure) {
        enclosures.removeIf(e -> e.getId().equals(enclosure.getId()));
        enclosures.add(enclosure);
    }

    public void deleteById(Long id) {
        enclosures.removeIf(e -> e.getId().equals(id));
    }
}
