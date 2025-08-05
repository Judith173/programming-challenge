package de.exxcellent.challenge;

import org.junit.jupiter.api.Test;

/**
 * JUnit 5 tests for testing the main App.
 */
class AppTest {


    @Test
    void runFootball() {
        App.main("--football", "football.csv");
    }
    @Test
    void runWeather() {
        App.main("--weather", "weather.csv");
    }


}