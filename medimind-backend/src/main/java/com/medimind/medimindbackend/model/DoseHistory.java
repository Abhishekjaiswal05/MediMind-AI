package com.medimind.medimindbackend.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class DoseHistory {

    private LocalDate date;

    private LocalTime time;

    private boolean taken;
}