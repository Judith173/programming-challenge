package de.exxcellent.challenge;
import de.exxcellent.challenge.service.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Junit 5 tests for testing the CSVReader class
 */
public class CSVReaderTest {

    private final String directory_name = "csv-reader-test-files/";
    private final String shared_directory_name = "shared-test-files/";

    @Test
    void readContentTest() {
        String file = directory_name + "simple-test-csv.csv";
        String[][] expected ={{"col1", "col2"},{"1", "2"},{"3", "4"}, {"5", "6"}};

        runTest(file, expected);
    }


    @Test
    void readContentTest_emptyCSV() {
        String file = shared_directory_name + "empty-csv.csv";
        String[][] expected ={};

        runTest(file, expected);
    }

    @Test
    void readContentTest_onlyHeaderCSV() {
        String file = shared_directory_name + "only-header-csv.csv";
        String[][] expected ={{"c1", "c2", "c3"}};

        runTest(file, expected);
    }

    @Test
    void readContentTest_csvWithEmptyLines() {
        String file = shared_directory_name + "empty-lines.csv";
        String[][] expected ={{"c1", "c2", "c3"},{"a","1","5"},{"b", "1", "2"}, {"c", "5", "1"}};
        runTest(file, expected);
    }

    @Test
    void readContentTest_nullArgument() {
       runTestWithIllegalArgument(null);
    }

    @Test
    void readContentTest_emptyFileName() {
        runTestWithIllegalArgument("");
    }

    @Test
    void readContentTest_whitespaceFileName() {
        runTestWithIllegalArgument("   ");
    }

    @Test
    void readContentTest_fileDoesNotExist() {
        try{
            CSVReader reader = new CSVReader("thisFileDoesNotExist.csv");
            reader.readContent();
            fail();
        }
        catch (FileNotFoundException e)
        {
            assertTrue(true);
        }

        catch (IOException e){
            System.err.println(e.getMessage());
            fail();
        }
    }

    private void runTest(String file, String[][] expected){
        try {
            CSVReader reader = new CSVReader(file);
            List<String[]> actual = reader.readContent();
            assertEquals(expected.length, actual.size());
            for (int i = 0; i < expected.length; i++)
            {
                assertArrayEquals(expected[i], actual.get(i));
            }

        }
        catch (IOException e){
            System.err.println(e.getMessage());
            fail();
        }
    }

    void runTestWithIllegalArgument(String file) {
        try{
            CSVReader reader = new CSVReader(file);
            reader.readContent();
            fail();
        }
        catch (IllegalArgumentException e)
        {
            assertTrue(true);
        }

        catch (IOException e){
            System.err.println(e.getMessage());
            fail();
        }
    }
}
