package de.exxcellent.challenge;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class FootballTest {
    private final String fileName = "football.csv";
    private final String targetIdentifier = "Team";
    private final String xIdentifier = "Goals";
    private final String yIdentifier = "Goals Allowed";

    private final int expectedNbrOfTeams = 1;
    private final String expectedTeam = "Aston_Villa";

    @Test
    public void findTargetsWithMinDist_football(){
        try{
            CSVMinDistTargetsFinder targetsFinder = new CSVMinDistTargetsFinder(fileName, targetIdentifier, xIdentifier, yIdentifier);
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

    private void evaluateResult(List<String> result)
    {
        assertEquals(expectedNbrOfTeams, result.size());
        assertEquals(expectedTeam, result.get(0));
    }
}
