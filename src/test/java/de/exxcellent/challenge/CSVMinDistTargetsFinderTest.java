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
    public void findTargetsWithMinDist_minDistIsZero() {
        String file = dirName + "min-dist-is-zero.csv";
        runTest(file, 1, "b");
    }

    @Test
    public void findTargetsWithMinDist_minDistInFloatingPointNumbers() {
        String file = dirName + "double-values.csv";
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

    @Test
    public void findTargetsWithMinDist_csvWithoutHeader(){
        String file = dirName + "csv-without-header.csv";
        runTestWithIllegalArgument(file, defaultTargetCol, defaultXIdentifier, defaultYIdentifier);
    }

    @Test
    public void findTargetsWithMinDist_incorrectTargetCol(){
        runTestWithIllegalArgument(defaultFile, "thisColDoesNotExist", defaultXIdentifier, defaultYIdentifier);
    }

    @Test
    public void findTargetsWithMinDist_incorrectXIdentifier(){
        runTestWithIllegalArgument(defaultFile, defaultTargetCol, "thisColDoesNotExist", defaultYIdentifier);
    }

    @Test
    public void findTargetsWithMinDist_incorrectYIdentifier(){
        runTestWithIllegalArgument(defaultFile, defaultTargetCol, defaultXIdentifier, "thisColDoesNotExist");
    }

    @Test
    public void findTargetsWithMinDist_fileIsNull(){
        runTestWithIllegalArgument(null, defaultTargetCol, defaultXIdentifier, "thisColDoesNotExist");
    }

    @Test
    public void findTargetsWithMinDist_targetColIsNull(){
        runTestWithIllegalArgument(defaultFile, null, defaultXIdentifier, "thisColDoesNotExist");
    }

    @Test
    public void findTargetsWithMinDist_xColIsNull(){
        runTestWithIllegalArgument(defaultFile, defaultTargetCol, null, "thisColDoesNotExist");
    }

    @Test
    public void findTargetsWithMinDist_yColIsNull(){
        runTestWithIllegalArgument(defaultFile, defaultTargetCol, defaultXIdentifier, null);
    }

    @Test
    public void findTargetsWithMinDist_valueCannotBeConvertedToNumber(){
        String file = dirName + "value-in-col-not-numeric.csv";
        runTestWithInvalidCSVFormat(file);
    }

    @Test
    public void findTargetsWithMinDist_missingValue(){
        String file = dirName + "csv-with-missing-value.csv";
        runTestWithInvalidCSVFormat(file);
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

    private void runTestWithIllegalArgument(String file, String targetId, String xId, String yID)
    {
        try {
            getResults(file, targetId, xId, yID);
            fail();
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private void runTestWithInvalidCSVFormat(String file)
    {
        try {
            CSVMinDistTargetsFinder targetsFinder = new CSVMinDistTargetsFinder(file, defaultTargetCol, defaultXIdentifier, defaultYIdentifier);
            targetsFinder.findTargetsWithMinDistance();
            fail();
        }
        catch (InvalidCSVFormatException e)
        {
            System.out.println(e.getMessage());
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
            fail();
        }
    }
}
