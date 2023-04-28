package com.example.flowcalculator.service;

import com.example.flowcalculator.data.model.CycleCalculation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class CycleCalculationServiceImplTest {
    @Autowired
    private CycleCalculationService service;

    @Test
    void calculateCycleDates() {
        String date = "10/04/2022";
        int days = 29;
        CycleCalculation cycleCalculation = service.calculateCycleDates(date, days);
        assertNotNull(cycleCalculation);
    }
}