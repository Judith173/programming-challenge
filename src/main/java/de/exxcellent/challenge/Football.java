package de.exxcellent.challenge;

import java.io.IOException;
import java.util.List;

public class Football {
    private final String csvFileName;
    private final String teamCol;
    private final String goalsCol;
    private final String goalsAllowedCol;

    public Football(String fileName){
        this.csvFileName = fileName;
        this.teamCol = "Team";
        this.goalsCol = "Goals";
        this.goalsAllowedCol = "Goals Allowed";
    }

    public Football(String fileName, String teamCol, String goalsCol, String goalsAllowedCol)
    {
        this.csvFileName = fileName;
        this.teamCol = teamCol;
        this.goalsCol = goalsCol;
        this.goalsAllowedCol = goalsAllowedCol;
    }

    public List<String> findTeamWithSmallestGoalDifference() throws IOException {
        CSVMinDistTargetsFinder teamsFinder = new CSVMinDistTargetsFinder(csvFileName, teamCol, goalsCol, goalsAllowedCol);
        return teamsFinder.findTargetsWithMinDistance();
    }

    public void printTeamsWithSmallestGoalDifference() throws IOException{
        List<String> teamsWithSmallestGoalDifference = findTeamWithSmallestGoalDifference();
        System.out.printf("Team(s) with smallest goal spread       : %s%n", String.join(", ", teamsWithSmallestGoalDifference));
    }

}
