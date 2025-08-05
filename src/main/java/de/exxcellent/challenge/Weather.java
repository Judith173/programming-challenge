package de.exxcellent.challenge;

import de.exxcellent.challenge.service.CSVMinDistTargetsFinder;

import java.io.IOException;
import java.util.List;

public class Weather {
    private final String csvFileName;
    private final String dayCol;
    private final String maxTempCol;
    private final String minTempCol;

    public Weather(String fileName){
        this.csvFileName = fileName;
        this.dayCol = "Day";
        this.maxTempCol = "MxT";
        this.minTempCol = "MnT";
    }

    public Weather(String fileName, String dayCol, String maxTempCol, String minTempCol)
    {
        this.csvFileName = fileName;
        this.dayCol = dayCol;
        this.maxTempCol = maxTempCol;
        this.minTempCol = minTempCol;
    }

    public List<String> findDaysWithMinTempSpread() throws IOException
    {
        CSVMinDistTargetsFinder daysFinder = new CSVMinDistTargetsFinder(csvFileName, dayCol, maxTempCol, minTempCol);
        return daysFinder.findTargetsWithMinDistance();
    }

    public void printDaysWithMinTempSpread() throws IOException{
        List<String> daysWithMinTempSpread = findDaysWithMinTempSpread();
        System.out.printf("Day(s) with smallest temperature spread : %s%n", String.join(", ", daysWithMinTempSpread));

    }
}
