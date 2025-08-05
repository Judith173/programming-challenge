package de.exxcellent.challenge;

import de.exxcellent.challenge.service.CSVMinDistTargetsFinder;

import java.io.IOException;
import java.util.List;

/**
 * Identifies the day(s) with the smallest temperature spread (= difference between minimum and maximum temperature)
 * by analyzing a csv-file.
 */
public class Weather {
    private final String csvFileName;
    private final String dayCol;
    private final String maxTempCol;
    private final String minTempCol;

    /**
     *
     * @param fileName csv-file with data about the weather including the minimum and maximum temperature on specific days
     */
    public Weather(String fileName){
        this.csvFileName = fileName;
        this.dayCol = "Day";
        this.maxTempCol = "MxT";
        this.minTempCol = "MnT";
    }

    /**
     *
     * @param fileName csv-file with data about the weather including the minimum and maximum temperature on specific days
     * @param dayCol column header of column with information about the day/date
     * @param maxTempCol column header of column with maximum temperature recorded on a given day
     * @param minTempCol column header of column with minimum temperature recorded on a given day
     */
    public Weather(String fileName, String dayCol, String maxTempCol, String minTempCol)
    {
        this.csvFileName = fileName;
        this.dayCol = dayCol;
        this.maxTempCol = maxTempCol;
        this.minTempCol = minTempCol;
    }

    /**
     * Finds the day(s) with the smallest temperature spread
     * @return list of days/dates. Has only one entry if there is a unique day with the smallest temperature spread
     * @throws IOException if an error occurs while reading the file or extracting relevant information from it
     */
    public List<String> findDaysWithMinTempSpread() throws IOException
    {
        CSVMinDistTargetsFinder daysFinder = new CSVMinDistTargetsFinder(csvFileName, dayCol, maxTempCol, minTempCol);
        return daysFinder.findTargetsWithMinDistance();
    }

    /**
     * Pretty prints the name of the day(s) with the smallest temperature spread
     * @throws IOException if an error occurs while reading the file or extracting relevant information from it
     */
    public void printDaysWithMinTempSpread() throws IOException{
        List<String> daysWithMinTempSpread = findDaysWithMinTempSpread();
        System.out.printf("Day(s) with smallest temperature spread : %s%n", String.join(", ", daysWithMinTempSpread));

    }
}
