package de.exxcellent.challenge;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CSVMinDistTargetsFinderTest {

    private final String dirName = "min-dist-finder-test-files/";
    private final String sharedDirName = "shared-test-files/";
    private final String defaultFile = dirName + "default-csv.csv";
    private final String defaultTargetCol = "c1";
    private final String defaultXIdentifier = "c2";
    private final String defaultYIdentifier = "c3";

    @Test
    public void findTargetsWithMinDist_default(){
        runTest(defaultFile, 1, "b");
    }

    @Test
    public void findTargetsWithMinDist_targetIsIdenticalToYIdentifier(){
        List<String> results = getResults(defaultFile, defaultYIdentifier, defaultXIdentifier, defaultYIdentifier);
        assertEquals(1, results.size());
        assertEquals("2", results.get(0));
    }


    @Test
    public void findTargetsWithMinDist_targetAndIdentifiersAreSameCol(){
        String[] expected = {"1", "1", "5"};
        List<String> results = getResults(defaultFile, defaultXIdentifier, defaultXIdentifier, defaultXIdentifier);
        assertEquals(expected.length, results.size());
        assertArrayEquals(expected, results.toArray());
    }

    @Test
    public void findTargetsWithMinDist_XandYIdentifierAreInterchanged(){
        List<String> results1 = getResults(defaultFile, defaultTargetCol, defaultXIdentifier, defaultYIdentifier);
        List<String> results2 = getResults(defaultFile, defaultTargetCol, defaultYIdentifier, defaultXIdentifier);

        assertEquals(1, results1.size(), results2.size());
        assertEquals("b", results1.get(0), results2.get(0));
    }

    @Test
    public void findTargetsWithMinDist_minDistInFirstRow(){
        String file = dirName + "min-dist-first-row.csv";
        runTest(file, 1, "a");
    }

    @Test
    public void findTargetsWithMinDist_minDistInLastRow(){
        String file = dirName + "min-dist-last-row.csv";
        runTest(file, 1, "c");
    }

    @Test
    public void findTargetsWithMinDist_minDistIsZero(){
        String file = dirName + "min-dist-is-zero.csv";
        runTest(file, 1, "b");
    }

    @Test
    public void findTargetsWithMinDist_multipleMinDistances(){
        String[] expected = {"a", "b", "c"};
        String file = dirName + "multiple-min-distances.csv";
        List<String> results = getResults(file, defaultTargetCol, defaultXIdentifier, defaultYIdentifier);
        assertEquals(expected.length, results.size());
        assertArrayEquals(expected, results.toArray());
    }

    @Test
    public void findTargetsWithMinDist_csvHasOneRow(){
        String file = dirName + "csv-with-one-row.csv";
        runTest(file, 1, "a");
    }

    @Test
    public void findTargetsWithMinDist_onlyHeaderCSV(){
        String file = sharedDirName + "only-header-csv.csv";
        runTest(file, 0, null);
    }

    @Test
    public void findTargetsWithMinDist_emptyCSV(){
        String file = sharedDirName + "empty-csv.csv";
        runTest(file, 0, null);
    }

    private void runTest(String file, int expectedLength, String expectedResult)
    {
        List<String> results = getResults(file, defaultTargetCol, defaultXIdentifier, defaultYIdentifier);
        assertEquals(expectedLength, results.size());
        if (expectedLength == 1){
            assertEquals(expectedResult, results.get(0));
        }

    }

    private List<String> getResults(String file, String targetId, String xId, String yID){
        try {
            CSVMinDistTargetsFinder targetsFinder = new CSVMinDistTargetsFinder(file, targetId, xId, yID);
            return targetsFinder.findTargetsWithMinDistance();
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
            fail();
        }
        return new ArrayList<>();
    }



}
