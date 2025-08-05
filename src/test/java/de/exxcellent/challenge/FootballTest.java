package de.exxcellent.challenge;

import de.exxcellent.challenge.service.MinDistTargetsFinderForTable;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Junit 5 Tests for testing the Football class
 */
public class FootballTest {
    private final String fileName = "football.csv";
    private final String targetIdentifier = "Team";
    private final String xIdentifier = "Goals";
    private final String yIdentifier = "Goals Allowed";

    @Test
    public void findTargetsWithMinDist_football(){
        try{
            MinDistTargetsFinderForTable targetsFinder = new MinDistTargetsFinderForTable(fileName, targetIdentifier, xIdentifier, yIdentifier);
            List<String> teamsWithSmallestGoalDiff = targetsFinder.findTargetsWithMinDistance();
            evaluateResult(teamsWithSmallestGoalDiff);
        }
        catch (IOException e){
            System.err.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void findTeamWithSmallestGoalDifference_constr1(){
        try{
            Football football = new Football(fileName);
            List<String> daysWithSmallestTempSpread = football.findTeamWithSmallestGoalDifference();
            evaluateResult(daysWithSmallestTempSpread);
        }
        catch (IOException e){
            System.err.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void findTeamWithSmallestGoalDifference_constr2(){
        try{
            Football football = new Football(fileName, targetIdentifier, xIdentifier, yIdentifier);
            List<String> daysWithSmallestTempSpread = football.findTeamWithSmallestGoalDifference();
            evaluateResult(daysWithSmallestTempSpread);
        }
        catch (IOException e){
            System.err.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void findTeamWithSmallestGoalDifference_nullFile()
    {
        runTestWithIllegalArgument(null, targetIdentifier, xIdentifier, yIdentifier);
    }

    @Test
    public void findTeamWithSmallestGoalDifference_nullTarget()
    {
        runTestWithIllegalArgument(fileName, null, xIdentifier, yIdentifier);
    }

    @Test
    public void findTeamWithSmallestGoalDifference_nullXIdentifier()
    {
        runTestWithIllegalArgument(fileName, targetIdentifier, null, yIdentifier);
    }

    @Test
    public void findTeamWithSmallestGoalDifference_nullYIdentifier()
    {
        runTestWithIllegalArgument(fileName, targetIdentifier, xIdentifier, null);
    }

    private void evaluateResult(List<String> result)
    {
        int expectedNbrOfTeams = 1;
        String expectedTeam = "Aston_Villa";
        assertEquals(expectedNbrOfTeams, result.size());
        assertEquals(expectedTeam, result.get(0));
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
