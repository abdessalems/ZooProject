package com.example.zootest.model;


import lombok.Data;

import java.util.List;

@Data
public class Enclosure {
    private Long id;
    private String name;
    private String size; // petit, moyen, grand
    private List<String> allowedSpecies; // Espèces permises

    public Enclosure() {
    }

    public Enclosure(Long id, String name, String size, List<String> allowedSpecies) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.allowedSpecies = allowedSpecies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public List<String> getAllowedSpecies() {
        return allowedSpecies;
    }

    public void setAllowedSpecies(List<String> allowedSpecies) {
        this.allowedSpecies = allowedSpecies;
    }
}
