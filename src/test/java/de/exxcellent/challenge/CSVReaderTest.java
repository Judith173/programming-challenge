package de.exxcellent.challenge;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class CSVReaderTest {
    @Test
    void transformCSVToArrayTest() {
        try{
            String[][] expected ={{"col1", "col2"},{"1", "2"},{"3", "4"}, {"5", "6"}};
            String[][] actual = CSVReader.transformCSVToArray("simpleTestCSV.csv");

            assertEquals(expected.length, actual.length);
            for (int i = 0; i < expected.length; i++)
            {
                assertArrayEquals(expected[i], actual[i]);
            }
        }
        catch (IOException e){
            System.err.println(e.getMessage());
            fail();
        }
    }

    @Test
    void transformCSVToArrayTest_emptyCSV() {
        try{
            String[][] actual = CSVReader.transformCSVToArray("emptyCSV.csv");
            assertEquals(0, actual.length);
        }
        catch (IOException e){
            System.err.println(e.getMessage());
            fail();
        }
    }

    @Test
    void transformCSVToArrayTest_onlyHeaderCSV() {
        try{
            String[][] expected ={{"c1", "c2"}};
            String[][] actual = CSVReader.transformCSVToArray("onlyHeaderCSV.csv");
            assertEquals(1, actual.length);
            assertArrayEquals(expected[0], actual[0]);
        }
        catch (IOException e){
            System.err.println(e.getMessage());
            fail();
        }
    }

    @Test
    void transformCSVToArrayTest_nullFile() {
        try{
            String[][] actual = CSVReader.transformCSVToArray(null);
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

    @Test
    void transformCSVToArrayTest_emptyFileName() {
        try{
            String[][] actual = CSVReader.transformCSVToArray("");
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

    @Test
    void transformCSVToArrayTest_whitespaceFileName() {
        try{
            String[][] actual = CSVReader.transformCSVToArray("   ");
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

    @Test
    void transformCSVToArrayTest_fileDoesNotExist() {
        try{
            String[][] actual = CSVReader.transformCSVToArray("thisFileDoesNotExist.csv");
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
}
