package com.example.flowcalculator.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "cycle_calculation")
public class CycleCalculation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate cycleStartDate;
    private LocalDate cycleEndDate;
    private LocalDate ovulationDate;
    private LocalDate safeStartDate;
    private LocalDate safeEndDate;
    @ElementCollection
    private List<LocalDate> twelveCycleDates;
}
