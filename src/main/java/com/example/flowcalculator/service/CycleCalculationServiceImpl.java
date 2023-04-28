package com.example.flowcalculator.service;

import com.example.flowcalculator.data.model.CycleCalculation;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CycleCalculationServiceImpl implements CycleCalculationService{


    @Override
    public CycleCalculation calculateCycleDates(String date, int cycleDays) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate lastCycleDate = LocalDate.parse(date, formatter);

        LocalDate cycleStartDate = lastCycleDate.plusDays(1);
        LocalDate cycleEndDate = cycleStartDate.plusDays(cycleDays - 1);

        int ovulationDay = (cycleDays - 14) % cycleDays;
        LocalDate ovulationDate = cycleStartDate.plusDays(ovulationDay);

        LocalDate safeStartDate = cycleStartDate.minusDays(ovulationDay);
        LocalDate safeEndDate = cycleEndDate.plusDays(cycleDays - ovulationDay - 1);

        LocalDate[] twelveCycleDates = new LocalDate[12];
        twelveCycleDates[0] = cycleStartDate.plusDays(cycleDays);
        for (int i = 1; i < 12; i++) {
            twelveCycleDates[i] = twelveCycleDates[i-1].plusDays(cycleDays);
        }

        CycleCalculation cycleDates = new CycleCalculation();
        cycleDates.setCycleStartDate(cycleStartDate);
        cycleDates.setCycleEndDate(cycleEndDate);
        cycleDates.setOvulationDate(ovulationDate);
        cycleDates.setSafeStartDate(safeStartDate);
        cycleDates.setSafeEndDate(safeEndDate);
        cycleDates.setTwelveCycleDates(List.of(twelveCycleDates));

        return cycleDates;
    }
}
