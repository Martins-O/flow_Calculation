package com.example.flowcalculator.service;

import com.example.flowcalculator.data.model.CycleCalculation;

public interface CycleCalculationService {
    CycleCalculation calculateCycleDates(String date, int cycleDays);
}
