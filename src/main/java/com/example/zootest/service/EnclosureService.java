package com.example.zootest.service;


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

    public List<Enclosure> findAll() {
        return enclosureRepository.findAll();
    }

    public Optional<Enclosure> findById(Long id) {
        return enclosureRepository.findById(id);
    }

    public void save(Enclosure enclosure) {
        enclosureRepository.save(enclosure);
    }

    public void deleteById(Long id) {
        enclosureRepository.deleteById(id);
    }
}
