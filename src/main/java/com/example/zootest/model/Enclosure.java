package com.example.zootest.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.util.List;

@Data
public class Enclosure {
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Size is required")
    private String size; // petit, moyen, grand

    @NotNull(message = "Allowed species are required")
    private List<String> allowedSpecies; // Espèces permises

    private List<Long> animalIds; // IDs des animaux dans cet enclos

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

    public List<Long> getAnimalIds() {
        return animalIds;
    }

    public void setAnimalIds(List<Long> animalIds) {
        this.animalIds = animalIds;
    }
}
