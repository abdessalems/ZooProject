package com.example.zootest.model;

import jakarta.validation.constraints.FutureOrPresent;
import lombok.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Visit {
    private Long id;

    @NotNull(message = "Date is required")
    @FutureOrPresent(message = "Date must be in the present or future")
    private LocalDateTime date;

    @NotNull(message = "Start time is required")
    private LocalDateTime startTime;

    @NotNull(message = "End time is required")
    private LocalDateTime endTime;

    @NotNull(message = "Enclosure IDs are required")
    private List<Long> enclosureIds; // IDs des enclos visités


}
