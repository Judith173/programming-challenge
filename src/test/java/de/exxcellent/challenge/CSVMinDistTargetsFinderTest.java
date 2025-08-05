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
    private final String sharedDirName = "shared-test-files/";
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
    public void findTargetsWithMinDist_targetAndIdentifiersAreSameCol(){
        try {
            String[] expected = {"1", "1", "5"};
            String file = dirName + defaultFile;
            CSVMinDistTargetsFinder targetsFinder = new CSVMinDistTargetsFinder(file, defaultXIdentifier, defaultXIdentifier, defaultXIdentifier);
            List<String> actual_results = targetsFinder.findTargetsWithMinDistance();
            assertEquals(expected.length, actual_results.size());
            assertArrayEquals(expected, actual_results.toArray());
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

    @Test
    public void findTargetsWithMinDist_minDistInFirstRow(){
        try {
            String file = dirName + "min-dist-first-row.csv";
            CSVMinDistTargetsFinder targetsFinder = new CSVMinDistTargetsFinder(file, defaultTargetCol, defaultXIdentifier, defaultYIdentifier);
            List<String> results = targetsFinder.findTargetsWithMinDistance();

            assertEquals(1, results.size());
            assertEquals("a", results.get(0));
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void findTargetsWithMinDist_minDistInLastRow(){
        try {
            String file = dirName + "min-dist-last-row.csv";
            CSVMinDistTargetsFinder targetsFinder = new CSVMinDistTargetsFinder(file, defaultTargetCol, defaultXIdentifier, defaultYIdentifier);
            List<String> results = targetsFinder.findTargetsWithMinDistance();

            assertEquals(1, results.size());
            assertEquals("c", results.get(0));
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void findTargetsWithMinDist_minDistIsZero(){
        try {
            String file = dirName + "min-dist-is-zero.csv";
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
    public void findTargetsWithMinDist_multipleMinDistances(){
        try {
            String[] expected = {"a", "b", "c"};
            String file = dirName + "multiple-min-distances.csv";
            CSVMinDistTargetsFinder targetsFinder = new CSVMinDistTargetsFinder(file, defaultTargetCol, defaultXIdentifier, defaultYIdentifier);
            List<String> actual_results = targetsFinder.findTargetsWithMinDistance();
            assertEquals(expected.length, actual_results.size());
            assertArrayEquals(expected, actual_results.toArray());
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void findTargetsWithMinDist_csvHasOneRow(){
        try {
            String file = dirName + "csv-with-one-row.csv";
            CSVMinDistTargetsFinder targetsFinder = new CSVMinDistTargetsFinder(file, defaultTargetCol, defaultXIdentifier, defaultYIdentifier);
            List<String> actual_results = targetsFinder.findTargetsWithMinDistance();
            assertEquals(1, actual_results.size());
            assertEquals("a", actual_results.get(0));
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void findTargetsWithMinDist_onlyHeaderCSV(){
        try {
            String file = sharedDirName + "only-header-csv.csv";
            CSVMinDistTargetsFinder targetsFinder = new CSVMinDistTargetsFinder(file, defaultTargetCol, defaultXIdentifier, defaultYIdentifier);
            List<String> actual_results = targetsFinder.findTargetsWithMinDistance();
            assertEquals(0, actual_results.size());
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void findTargetsWithMinDist_emptyCSV(){
        try {
            String file = sharedDirName + "empty-csv.csv";
            CSVMinDistTargetsFinder targetsFinder = new CSVMinDistTargetsFinder(file, defaultTargetCol, defaultXIdentifier, defaultYIdentifier);
            List<String> actual_results = targetsFinder.findTargetsWithMinDistance();
            assertEquals(0, actual_results.size());
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
            fail();
        }
    }



}
