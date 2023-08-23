package com.schmitt.population_projection;

/**
 * @author Nathan Schmitt
 */
public class Population_projection {

    public static void main(String[] args) {
        // Initial population
        long currentPopulation = 312032486;

        // Constants for births, deaths, and immigrants per second
        final double BIRTHS_PER_SECOND = 1.0 / 7.0;
        final double DEATHS_PER_SECOND = 1.0 / 13.0;
        final double IMIGRANTS_PER_SECOND = 1.0 / 45.0;

        // Seconds in a year
        final long SECONDS_IN_YEAR = 365 * 24 * 60 * 60;
        
        //How many years should we calculate?
        final int YEARS_TO_CALCULATE = 5;

        // Calculate population for each of the next five years
        for (int year = 1; year <= YEARS_TO_CALCULATE; year++) {
            // Calculate net change in population
            double netChange = SECONDS_IN_YEAR * (BIRTHS_PER_SECOND - DEATHS_PER_SECOND + IMIGRANTS_PER_SECOND);

            // Update current population by adding the net change
            currentPopulation += netChange;

            // Display population for the current year
            System.out.println("Year " + year + ": " + currentPopulation);
        }
    }
}
