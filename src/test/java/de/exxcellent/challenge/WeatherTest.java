package de.exxcellent.challenge;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class WeatherTest {

    private final String fileName = "weather.csv";
    private final String targetIdentifier = "Day";
    private final String xIdentifier = "MxT";
    private final String yIdentifier = "MnT";

    private final int expectedNbrOfDays = 1;
    private final String expectedDay = "14";

    @Test
    public void findTargetsWithMinDist_weather(){
        try{
            CSVMinDistTargetsFinder targetsFinder = new CSVMinDistTargetsFinder(fileName, targetIdentifier, xIdentifier, yIdentifier);
            List<String> daysWithSmallestTempSpread = targetsFinder.findTargetsWithMinDistance();
            evaluateResult(daysWithSmallestTempSpread);
        }
        catch (IOException e){
            System.err.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void findDaysWithMinTempSpread_constr1(){
        try{
            Weather weather = new Weather(fileName);
            List<String> daysWithSmallestTempSpread = weather.findDaysWithMinTempSpread();
            evaluateResult(daysWithSmallestTempSpread);
        }
        catch (IOException e){
            System.err.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void findDaysWithMinTempSpread_constr2(){
        try{
            Weather weather = new Weather(fileName, targetIdentifier, xIdentifier, yIdentifier);
            List<String> daysWithSmallestTempSpread = weather.findDaysWithMinTempSpread();
            evaluateResult(daysWithSmallestTempSpread);
        }
        catch (IOException e){
            System.err.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void findDaysWithMinTempSpread_nullFile()
    {
        runTestWithIllegalArgument(null, targetIdentifier, xIdentifier, yIdentifier);
    }

    @Test
    public void findDaysWithMinTempSpread_nullTarget()
    {
        runTestWithIllegalArgument(fileName, null, xIdentifier, yIdentifier);
    }

    @Test
    public void findDaysWithMinTempSpread_nullXIdentifier()
    {
        runTestWithIllegalArgument(fileName, targetIdentifier, null, yIdentifier);
    }

    @Test
    public void findDaysWithMinTempSpread_nullYIdentifier()
    {
        runTestWithIllegalArgument(fileName, targetIdentifier, xIdentifier, null);
    }

    private void evaluateResult(List<String> result)
    {
        assertEquals(expectedNbrOfDays, result.size());
        assertEquals(expectedDay, result.get(0));
    }

    private void runTestWithIllegalArgument(String fileName, String targetIdentifier, String xIdentifier, String yIdentifier)
    {
        try {
            Football football = new Football(fileName, targetIdentifier, xIdentifier, yIdentifier);
            football.findTeamWithSmallestGoalDifference();
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
            fail();
        }
    }

}
