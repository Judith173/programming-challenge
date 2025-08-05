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
