package de.exxcellent.challenge;

import de.exxcellent.challenge.service.CSVMinDistTargetsFinder;

import java.io.IOException;
import java.util.List;

/**
 * Identifies the football team(s) with the smallest goal difference (= smallest distance between scored goals and goals
 * scored against them) by analyzing a csv-file.
 */
public class Football {
    private final String csvFileName;
    private final String teamCol;
    private final String goalsCol;
    private final String goalsAllowedCol;

    /**
     *
     * @param fileName csv-file with data about the football teams, their scored goals and gaols scored against them
     */
    public Football(String fileName){
        this.csvFileName = fileName;
        this.teamCol = "Team";
        this.goalsCol = "Goals";
        this.goalsAllowedCol = "Goals Allowed";
    }

    /**
     *
     * @param fileName csv-file with data about the football teams, their scored goals and gaols scored against them
     * @param teamCol column header of column with information about the team names
     * @param goalsCol column header of column with scored goals
     * @param goalsAllowedCol column header of column with information about goals scored against a specific team
     */
    public Football(String fileName, String teamCol, String goalsCol, String goalsAllowedCol)
    {
        this.csvFileName = fileName;
        this.teamCol = teamCol;
        this.goalsCol = goalsCol;
        this.goalsAllowedCol = goalsAllowedCol;
    }

    /**
     * Finds the team(s) with the smallest goal difference
     * @return list of team names. Has only one entry if there is a unique team with the smallest goal difference
     * @throws IOException if an error occurs while reading the file or extracting relevant information from it
     */
    public List<String> findTeamWithSmallestGoalDifference() throws IOException {
        CSVMinDistTargetsFinder teamsFinder = new CSVMinDistTargetsFinder(csvFileName, teamCol, goalsCol, goalsAllowedCol);
        return teamsFinder.findTargetsWithMinDistance();
    }

    /**
     * Pretty prints the name of the team(s) with the smallest goal difference
     * @throws IOException if an error occurs while reading the file or extracting relevant information from it
     */
    public void printTeamsWithSmallestGoalDifference() throws IOException{
        List<String> teamsWithSmallestGoalDifference = findTeamWithSmallestGoalDifference();
        System.out.printf("Team(s) with smallest goal spread       : %s%n", String.join(", ", teamsWithSmallestGoalDifference));
    }

}
