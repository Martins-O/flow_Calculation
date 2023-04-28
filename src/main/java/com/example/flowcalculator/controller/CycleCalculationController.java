package com.example.flowcalculator.controller;

import com.example.flowcalculator.data.model.CycleCalculation;
import com.example.flowcalculator.service.CycleCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "cycle", path = "api/v1/cycle")
public class CycleCalculationController {
    @Autowired
    private CycleCalculationService service;
    @GetMapping("/cycle-dates")
    public ResponseEntity<?> getCycleDates(
            @RequestParam(value = "date") String date,
            @RequestParam(value = "cycleDays") int cycleDays) {
        CycleCalculation calculation = service.calculateCycleDates(date, cycleDays);
        return ResponseEntity.ok().body(calculation);
    }
}
