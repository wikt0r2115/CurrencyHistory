package com.przelicznikwalut;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DataValidation {
    final Integer y = 2002;
    final Integer m = 1;
    final Integer d = 2;
    public Boolean DateValidation(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            // Parsowanie daty wejściowej
            LocalDate inputDate = LocalDate.parse(date, formatter);
            // Definiowanie daty referencyjnej (2 stycznia 2002)
            LocalDate referenceDate = LocalDate.of(this.y, this.m, this.d);

            // Sprawdzanie, czy data wejściowa jest późniejsza niż data referencyjna
            if (inputDate.isAfter(referenceDate))
                return Boolean.TRUE;
            else
                return Boolean.FALSE;

        } catch (DateTimeParseException e) {
            return Boolean.FALSE;
        }
    }
}
