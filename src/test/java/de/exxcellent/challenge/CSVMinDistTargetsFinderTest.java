package de.exxcellent.challenge;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CSVMinDistTargetsFinderTest {

    @Test
    public void findTargetsWithMinDist_weather(){
        CSVMinDistTargetsFinder targetsFinder = new CSVMinDistTargetsFinder("weather.csv", "Day", "MnT", "MxT");
        String expected_day = "14";
        try{
            List<String> daysWithSmallestTempSpread = targetsFinder.findTargetsWithMinDistance();
            assertEquals(1, daysWithSmallestTempSpread.size());
            assertEquals(expected_day, daysWithSmallestTempSpread.get(0));
            }
        catch (IOException e){
            System.err.println(e.getMessage());
            fail();

    }}

}
