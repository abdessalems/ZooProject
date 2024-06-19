package com.example.zootest.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;



@Data
public class Animal {
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Species is required")
    private String species;

    @NotNull(message = "Age is required")
    private Integer age;

    @NotNull(message = "Enclosure ID is required")
    private Long enclosureId; // ID de l'enclos assigné

    public Animal(Long id, String name, String species, Integer age, Long enclosureId) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.age = age;
        this.enclosureId = enclosureId;
    }

    public Animal() {
    }
}
