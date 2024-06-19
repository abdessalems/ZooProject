package com.example.zootest.model;


import lombok.Data;

@Data
public class Animal {
    private Long id;
    private String name;
    private String species;
    private int age;
    private Long enclosureId; // ID de l'enclos assigné

    public Animal(Long id, String name, String species, int age, Long enclosureId) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.age = age;
        this.enclosureId = enclosureId;
    }

    public Animal() {
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

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getEnclosureId() {
        return enclosureId;
    }

    public void setEnclosureId(Long enclosureId) {
        this.enclosureId = enclosureId;
    }
}
