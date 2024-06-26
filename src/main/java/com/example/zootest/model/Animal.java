package com.example.zootest.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;

@Data
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Animal {
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Species is required")
    private Species species;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateDeNaissance; // Date de naissance

    @NotNull(message = "Enclosure ID is required")
    private Long enclosureId; // ID de l'enclos assigné


    // Méthode pour calculer l'âge en fonction de la date de naissance
    public int getAge() {
        if (dateDeNaissance == null) {
            return 0; // Si la date de naissance n'est pas définie, retourner 0 ou une autre valeur par défaut.
        }
        return Period.between(dateDeNaissance, LocalDate.now()).getYears();
    }

}
