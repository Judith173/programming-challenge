package de.exxcellent.challenge;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SmallestTempSpreadDetTest {

    @Test
    public void findTargetWithSmallestTempSpreadTest(){
        SmallestTemperatureSpreadDeterminer stsd = new SmallestTemperatureSpreadDeterminer("weather.csv", "Day", "MnT", "MxT");
        String expected = "14";
        try{
            ArrayList<String> smallestTempSpread = stsd.findTargetWithSmallestTempSpread();
            assertEquals(1, smallestTempSpread.size());
            assertEquals(expected, smallestTempSpread.get(0));
            }
        catch (IOException e){
            System.err.println(e.getMessage());
            fail();

    }}

}
