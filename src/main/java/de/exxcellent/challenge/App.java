package de.exxcellent.challenge;


import java.io.IOException;
import java.util.List;

/**
 * Main class to run Football or Weather by using ars.
 */
public final class App {

    /**
     * Run class Weather or Football with data from csv-file
     * @param args <--weather|--football> <myFile.csv>
     */
    public static void main(String... args) {
        List<String> validArgs = List.of("--weather", "--football");
        if (args.length != 2 || !validArgs.contains(args[0])){
            System.err.printf("Error: Two arguments expected: <'%s'|'%s'> <'csv-file-name'>", validArgs.get(0), validArgs.get(1));
            System.err.println("\nIf you try to execute this with Maven try: mvn exec:java -Dexec.args=\"argument1 argument2\"");
            System.exit(1);
        }

        String mode = args[0];
        String fileName = args[1];

        try {
            if (mode.equals(validArgs.get(0)))
            {
                Weather weather = new Weather(fileName);
                weather.printDaysWithMinTempSpread();
            }
            else if (mode.equals(validArgs.get(1))) {
                Football football = new Football(fileName);
                football.printTeamsWithSmallestGoalDifference();
            }
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
    }
}
