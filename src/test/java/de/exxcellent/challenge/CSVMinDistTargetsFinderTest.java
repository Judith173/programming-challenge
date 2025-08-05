package de.exxcellent.challenge;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CSVMinDistTargetsFinderTest {

    private final String dirName = "min-dist-finder-test-files/";
    private final String defaultFile = "default-csv.csv";
    private final String defaultTargetCol = "c1";
    private final String defaultXIdentifier = "c2";
    private final String defaultYIdentifier = "c3";

    @Test
    public void findTargetsWithMinDist_default(){
        try {
            String file = dirName + defaultFile;
            CSVMinDistTargetsFinder targetsFinder = new CSVMinDistTargetsFinder(file, defaultTargetCol, defaultXIdentifier, defaultYIdentifier);
            List<String> actual_results = targetsFinder.findTargetsWithMinDistance();
            assertEquals(1, actual_results.size());
            assertEquals("b", actual_results.get(0));
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void findTargetsWithMinDist_targetIsIdenticalToYIdentifier(){
        try {
            String file = dirName + defaultFile;
            CSVMinDistTargetsFinder targetsFinder = new CSVMinDistTargetsFinder(file, defaultYIdentifier, defaultXIdentifier, defaultYIdentifier);
            List<String> actual_results = targetsFinder.findTargetsWithMinDistance();
            assertEquals(1, actual_results.size());
            assertEquals("2", actual_results.get(0));
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void findTargetsWithMinDist_XandYIdentifierAreInterchanged(){
        try {
            String file = dirName + defaultFile;
            CSVMinDistTargetsFinder targetsFinder = new CSVMinDistTargetsFinder(file, defaultTargetCol, defaultXIdentifier, defaultYIdentifier);
            CSVMinDistTargetsFinder targetsFinderInterchanged = new CSVMinDistTargetsFinder(file, defaultTargetCol, defaultYIdentifier, defaultXIdentifier);

            List<String> results = targetsFinder.findTargetsWithMinDistance();
            List<String> resultsInterchanged = targetsFinderInterchanged.findTargetsWithMinDistance();
            assertEquals(1, results.size(), resultsInterchanged.size());
            assertEquals("b", results.get(0), resultsInterchanged.get(0));
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
            fail();
        }
    }



}
