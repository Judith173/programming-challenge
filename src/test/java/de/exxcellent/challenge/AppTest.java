package de.exxcellent.challenge;

import org.junit.jupiter.api.Test;

/**
 * Example JUnit 5 test case.
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
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