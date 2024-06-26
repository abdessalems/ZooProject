package com.example.zootest.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@ToString
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
}
