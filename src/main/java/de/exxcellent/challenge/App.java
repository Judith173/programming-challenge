package de.exxcellent.challenge;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {

    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {

        try{
        List<String[]> content = CSVReader.getFileContent("weather.csv");

        for (String[] line : content){
            for (String element : line){
                System.out.print(element + " ");
            }
            System.out.println();
        }
        CSVMinDistTargetsFinder c = new CSVMinDistTargetsFinder("weather.csv", "Day", "MnT", "MxT");
        List<String> smallestTempSpread = c.findTargetsWithMinDistance();
        for (String element: smallestTempSpread){
            System.out.println(element);
        }

        }
        catch (IOException e){
            System.err.println(e.getMessage());
        }





        // Your preparation code …

        String dayWithSmallestTempSpread = "Someday";     // Your day analysis function call …
        System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);

        String teamWithSmallestGoalSpread = "A good team"; // Your goal analysis function call …
        System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);
    }
}
